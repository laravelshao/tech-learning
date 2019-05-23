package com.laravelshao.learning.redis.jedis;

/**
 * @author shaoqinghua
 * @date 2018/11/17
 * @description 测试Jedis分布式锁
 */
public class JedisDistributedLockTest {

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    JedisDistributedLock lock = new JedisDistributedLock();
                    String result = lock.acquireLock("updateOrder", 2000, 5000);
                    if (result != null) {
                        System.out.println(Thread.currentThread().getName() + "->成功获取锁：" + result);
                        try {
                            Thread.sleep(1000);
                            // 释放锁
                            lock.releaseLock("updateOrder", result);
                            //lock.releaseLockWithLua("updateOrder", result);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }).start();
        }

    }
}
