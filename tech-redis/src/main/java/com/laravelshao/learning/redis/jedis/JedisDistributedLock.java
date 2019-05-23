package com.laravelshao.learning.redis.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.UUID;

/**
 * @author shaoqinghua
 * @date 2018/11/17
 * @description
 */
public class JedisDistributedLock {

    /**
     * 获取锁
     *
     * @param lockName       锁名称
     * @param acquireTimeout 获取锁超时时间
     * @param lockTimeout    锁过期时间(防止死锁)
     * @return
     */
    public String acquireLock(String lockName, long acquireTimeout, long lockTimeout) {
        // 保证获取锁于释放锁是同一个线程
        String identifier = UUID.randomUUID().toString();
        String lockKey = "lock:" + lockName;
        int lockExpire = (int) (lockTimeout / 1000);
        Jedis jedis = null;

        try {
            jedis = JedisUtil.getJedis();
            long end = System.currentTimeMillis() + acquireTimeout;
            // 获取锁超时时间范围内
            while (System.currentTimeMillis() < end) {
                if (jedis.setnx(lockKey, identifier) == 1) { // 设置成功
                    jedis.expire(lockKey, lockExpire); // 设置超时时间
                    return identifier; // 返回获取成功
                }

                if (jedis.ttl(lockKey) == -1) {
                    jedis.expire(lockKey, lockExpire);
                }
                try {
                    Thread.sleep(100); // 等待重试
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            jedis.close(); // 关闭连接
        }
        return null;
    }

    /**
     * 释放锁
     *
     * @param lockName   锁名称
     * @param identifier
     * @return
     */
    public boolean releaseLock(String lockName, String identifier) {
        System.out.println(lockName + "->开始释放锁：" + identifier);
        String lockKey = "lock:" + lockName;
        Jedis jedis = null;
        boolean isRelease = false;
        try {
            jedis = JedisUtil.getJedis();
            while (true) {
                jedis.watch(lockKey); // watch：key被其它线程修改，transaction执行会返回空
                // 判断是否为同一把锁
                if (identifier.equals(jedis.get(lockKey))) {
                    Transaction transaction = jedis.multi();
                    transaction.del(lockKey);
                    if (transaction.exec().isEmpty()) {
                        continue;
                    }
                    isRelease = true;
                }
                // TODO 异常
                jedis.unwatch();
                break;
            }
        } finally {
            jedis.close();
        }
        return isRelease;
    }

    /**
     * 使用lua脚本方式释放锁(lua脚本方式可以保证原子性)
     *
     * @param lockName   锁名称
     * @param identifier
     * @return
     */
    public boolean releaseLockWithLua(String lockName, String identifier) {
        System.out.println(lockName + "->开始释放锁：" + identifier);
        String lockKey = "lock:" + lockName;
        Jedis jedis = JedisUtil.getJedis();
        String lua = "if redis.call(\"get\",KEYS[1])==ARGV[1] then " +
                "return redis.call(\"del\",KEYS[1]) " +
                "else return 0 end";
        Long rs = (Long) jedis.eval(lua, 1, new String[]{lockKey, identifier});
        if (rs.intValue() > 0) {
            return true;
        }
        return false;
    }

}
