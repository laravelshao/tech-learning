package com.laravelshao.learning.concurrent;

/**
 * 实现多线程方式1：继承Thread类，重写run方法
 */
public class MyThreadTest extends Thread {

    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("MyThread run i = " + i);
        }
    }

    public static void main(String[] args) {

        // 创建线程
        MyThreadTest t1 = new MyThreadTest();
        // 启动线程
        t1.start();
    }
}
