package com.laravelshao.learning.redis.jedis.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.UUID;

/**
 * @author shaoqinghua
 * @date 2019/2/7
 * @description Jedis实现分布式锁
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
    public String acquire(String lockName, long acquireTimeout, long lockTimeout) {

        // 设置唯一标识 保证获取锁与释放锁是同一线程 也可以用线程ID作为标识
        String identifier = UUID.randomUUID().toString();
        String lockKey = "lock:" + lockName;
        int lockExpire = (int) lockTimeout / 1000;

        Jedis jedis = null;
        try {
            jedis = JedisConnectUtil.getJedis();
            long end = System.currentTimeMillis() + acquireTimeout;
            while (System.currentTimeMillis() < end) {

                if (jedis.setnx(lockKey, identifier) == 1) { // 获取锁成功
                    jedis.expire(lockKey, lockExpire); // 设置超时时间
                    return identifier;
                }

                if (jedis.ttl(lockKey) == -1) {
                    jedis.expire(lockKey, lockExpire);
                }

                try {
                    Thread.sleep(1000); // 休眠1秒重试
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            jedis.close();
        }
        return null;
    }

    /**
     * 释放锁
     *
     * @param lockName   锁名称
     * @param identifier 锁标识
     * @return
     */
    public boolean releaseLock(String lockName, String identifier) {

        System.out.println(Thread.currentThread().getName() + "->开始释放锁：" + identifier);
        String lockKey = "lock:" + lockName;

        boolean isRelease = false; // 锁释放标识
        Jedis jedis = null;
        try {
            jedis = JedisConnectUtil.getJedis();
            while (true) {
                jedis.watch(lockKey);
                if (identifier.equals(jedis.get(lockKey))) { // 判断是否同一把锁
                    Transaction tx = jedis.multi();
                    tx.del(lockKey);
                    if (tx.exec().isEmpty()) {
                        continue;
                    }
                    isRelease = true;
                }

                jedis.unwatch();
                break;
            }
        } finally {
            jedis.close();
        }

        return isRelease;
    }

}
