package com.laravelshao.learning.concurrent.volatiletest;

/**
 * 线程优雅关闭测试
 *
 * @author qinghua.shao
 * @date 2022/6/19
 * @since 1.0.0
 */
public class VolatileTest2 {

    public volatile static Boolean stop = false;

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
    }
}