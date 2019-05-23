package com.laravelshao.learning.concurrent;

/**
 * @author shaoqinghua
 * @date 2018/9/19
 * @description
 */
public class VolatileDemo {
    public volatile static Boolean stop = false;

    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            int i = 0;
            while (!stop) {
                i++;
            }
        });
        thread.start();
        System.out.println("begin start thread");
        Thread.sleep(1000);
        stop = true;

//        Thread t1 = new Thread(() -> {
//            a = 1;
//            x = b;
//        });
//        Thread t2 = new Thread(() -> {
//            b = 1;
//            y = a;
//        });
//        t1.start();
//        t2.start();
//        t1.join();
//        t2.join();
//        System.out.println("x=" + x + "->y=" + y);


    }
}
