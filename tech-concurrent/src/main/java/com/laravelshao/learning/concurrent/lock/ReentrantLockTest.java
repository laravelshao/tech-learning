package com.laravelshao.learning.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 测试
 *
 * @author qinghua.shao
 * @date 2022/7/2
 * @since 1.0.0
 */
public class ReentrantLockTest {

    static int num = 0;
    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    lock.lock();
                    num++;
                    System.out.println(num);
                    lock.unlock();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    lock.lock();
                    num++;
                    System.out.println(num);
                    lock.unlock();
                }
            }
        }.start();
    }
}
