package com.laravelshao.learning.concurrent.question;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shaoqinghua
 * @date 2019/2/11
 * @description 三个线程交替打印ABC(lock实现)
 */
public class PrintABC implements Runnable {

    private Lock lock;
    private String letter; // 当前线程打印字母
    private int flag; // 放行标识

    private static int number = 0;

    public PrintABC(Lock lock, String letter, int flag) {
        this.lock = lock;
        this.letter = letter;
        this.flag = flag;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; ) {
            try {
                lock.lock();
                while (number % 3 == flag) {// 多线程并发，不能用if，必须用循环测试等待条件，避免虚假唤醒
                    System.out.println(Thread.currentThread().getName() + "->" + letter);
                    number++;
                    i++;
                }
            } finally {
                lock.unlock();// unlock()操作必须放在finally块中
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        new Thread(new PrintABC(lock, "A", 0), "Thread-A").start();
        new Thread(new PrintABC(lock, "B", 1), "Thread-B").start();
        new Thread(new PrintABC(lock, "C", 2), "Thread-C").start();
    }
}
