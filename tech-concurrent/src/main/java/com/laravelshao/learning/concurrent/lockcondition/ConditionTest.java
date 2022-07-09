package com.laravelshao.learning.concurrent.lockcondition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition 测试
 *
 * @author qinghua.shao
 * @date 2018/7/2
 * @since 1.0.0
 */
public class ConditionTest {

    static int num = 0;
    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static void main(String[] args) throws Exception {

        new Thread() {
            @Override
            public void run() {
                System.out.println("第一个线程加锁");
                lock.lock();
                try {
                    System.out.println("第一个线程释放锁并阻塞等待");
                    condition.await();
                    System.out.println("第一个线程重新获取到锁");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
                System.out.println("第一个线程释放锁");
            }
        }.start();

        Thread.sleep(1000);

        new Thread() {
            @Override
            public void run() {
                System.out.println("第二个线程加锁");
                lock.lock();
                System.out.println("第二个线程唤醒第一个线程");
                condition.signal();
                System.out.println("第二个线程释放锁");
                lock.unlock();
            }
        }.start();
    }
}
