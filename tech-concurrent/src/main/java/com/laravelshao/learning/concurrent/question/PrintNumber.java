package com.laravelshao.learning.concurrent.question;


/**
 * @author shaoqinghua
 * @date 2019/2/11
 * @description 等待唤醒测试 两个线程交替打印1-100 线程1打印出奇数 线程2打印出偶数(wait/notify实现)
 */
public class PrintNumber implements Runnable {

    private int i = 1;

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                notify();
                if (i <= 100) {
                    System.out.println(Thread.currentThread().getName() + "->" + i);
                    i++;
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        PrintNumber task = new PrintNumber();
        new Thread(task, "Thread-0").start();
        new Thread(task, "Thread-1").start();
    }
}