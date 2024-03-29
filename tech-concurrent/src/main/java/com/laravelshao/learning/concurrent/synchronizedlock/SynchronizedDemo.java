package com.laravelshao.learning.concurrent.synchronizedlock;

/**
 * @author shaoqinghua
 * @date 2018/9/20
 * @description
 */
public class SynchronizedDemo {

    private static int count = 0;

    public static void inc() {
        synchronized (SynchronizedDemo.class) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                SynchronizedDemo.inc();
            }).start();
        }

        Thread.sleep(3000);
        System.out.println("执行结果：" + count);
    }

}
