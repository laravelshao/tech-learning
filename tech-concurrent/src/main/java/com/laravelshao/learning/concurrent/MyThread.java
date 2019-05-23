package com.laravelshao.learning.concurrent;

/**
 * Created by LaravelShao on 2017/12/14.
 */
public class MyThread extends Thread {

    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("MyThread run i = " + i);
        }
    }

}
