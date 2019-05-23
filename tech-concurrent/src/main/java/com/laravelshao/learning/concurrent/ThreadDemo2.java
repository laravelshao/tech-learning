package com.laravelshao.learning.concurrent;

/**
 * Created by LaravelShao on 2017/12/14.
 */
public class ThreadDemo2 {

    public static void main(String[] args) {

        /**
         * 实现多线程方式2：实现Runnable接口，实现run方法
         */

        //创建实现类的对象
        MyThread2 my = new MyThread2();

        //创建Thread类的对象，将实现类对象作为参数传递
        Thread t = new Thread(my);

        //开启新线程
        t.start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("Main run i = " + i);
        }

    }
}
