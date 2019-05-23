package com.laravelshao.learning.zookeeper.test;


import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author shaoqinghua
 * @date 2019/1/30
 * @description
 */
public class ZkDistributedLockTest {

    public static void main(String[] args) throws IOException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                    ZkDistributedLock zkDistributedLock = new ZkDistributedLock();
                    zkDistributedLock.lock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "Thread-" + i).start();
            countDownLatch.countDown();
        }
        System.in.read();
    }
}
