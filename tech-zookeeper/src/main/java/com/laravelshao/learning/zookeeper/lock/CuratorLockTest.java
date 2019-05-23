package com.laravelshao.learning.zookeeper.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.CountDownLatch;

/**
 * @author shaoqinghua
 * @date 2019/1/30
 * @description
 */
public class CuratorLockTest {

    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString("172.16.105.128:2181")
                .sessionTimeoutMs(4000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .namespace("curator")
                .build();

        curatorFramework.start();

        InterProcessMutex interProcessMutex = new InterProcessMutex(curatorFramework, "/curator/lock");
        //// 获取锁
        //interProcessMutex.acquire();
        //// 释放锁
        //interProcessMutex.release();

        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                    interProcessMutex.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "Thread-" + i).start();
            countDownLatch.countDown();
        }
        System.in.read();
    }
}
