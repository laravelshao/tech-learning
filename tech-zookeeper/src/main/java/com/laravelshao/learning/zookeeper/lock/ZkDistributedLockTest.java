package com.laravelshao.learning.zookeeper.lock;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author shaoqinghua
 * @date 2018/10/21
 * @description zookeeper实现分布式锁测试
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
