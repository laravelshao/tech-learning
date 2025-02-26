package com.laravelshao.learning.question.PrintNumber;


/**
 * 【等待唤醒测试】两个线程交替打印1-100 线程1打印出奇数 线程2打印出偶数(wait/notify实现)
 *
 * @author shaoqinghua
 * @date 2019/2/11
 */
public class PrintNumberTask1 implements Runnable {

    private int i = 1;

    public static void main(String[] args) {
        PrintNumberTask1 task = new PrintNumberTask1();
        new Thread(task, "Thread-0").start();
        new Thread(task, "Thread-1").start();
    }

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
}