package com.laravelshao.learning.question.PrintNumber;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 【等待唤醒测试】两个线程交替打印1-100 线程1打印出奇数 线程2打印出偶数(await/signal实现)
 *
 * @author shaoqinghua
 * @date 2019/2/11
 */
public class PrintNumberTask2 implements Runnable {

    private ReentrantLock lock;
    private Condition condition;
    private int flag;

    //private static AtomicInteger i = new AtomicInteger(1); // 必须是静态才能全局共享
    private static int i = 1; // 必须是静态才能全局共享

    public PrintNumberTask2(ReentrantLock lock, Condition condition, int flag) {
        this.lock = lock;
        this.condition = condition;
        this.flag = flag;
    }

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                try {
                    while (i % 2 != flag) {
                        condition.await();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (i > 100) {
                    break;
                }
                System.out.println(Thread.currentThread().getName() + "->" + i);
                i++;
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(new PrintNumberTask2(lock, condition, 1), "Thread-0").start();
        new Thread(new PrintNumberTask2(lock, condition, 0), "Thread-1").start();

        // 三个线程交替打印1-100
        //new Thread(new PrintNumberTask2(lock, condition, 1), "Thread-0").start();
        //new Thread(new PrintNumberTask2(lock, condition, 2), "Thread-1").start();
        //new Thread(new PrintNumberTask2(lock, condition, 0), "Thread-2").start();
    }

}
