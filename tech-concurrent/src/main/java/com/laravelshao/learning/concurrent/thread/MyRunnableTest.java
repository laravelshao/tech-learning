package com.laravelshao.learning.concurrent.thread;

/**
 * 实现多线程方式2：实现Runnable接口，实现run方法
 */
public class MyRunnableTest implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("MyThread run i = " + i);
        }
    }

    public static void main(String[] args) {
        // 创建实现类的对象
        MyRunnableTest runnable = new MyRunnableTest();
        // 创建Thread类的对象，将实现类对象作为参数传递
        Thread t = new Thread(runnable);
        // 启动线程
        t.start();
    }
}
