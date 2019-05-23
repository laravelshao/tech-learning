package com.laravelshao.learning.concurrent;

/**
 * Created by LaravelShao on 2017/12/14.
 */
public class ThreadDemo1 {

    public static void main(String[] args) {
        /**
         * 实现多线程方式1：继承Thread类，重写run方法
         */

        //创建线程
        MyThread t1 = new MyThread();

        //启动线程
        t1.start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("Main run i = " + i);
        }
    }
}
