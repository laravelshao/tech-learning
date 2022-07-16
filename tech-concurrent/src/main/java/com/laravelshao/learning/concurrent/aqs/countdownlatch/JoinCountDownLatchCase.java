package com.laravelshao.learning.concurrent.aqs.countdownlatch;

/**
 * @author qinghua.shao
 * @date 2018/5/19
 * @since 1.0.0
 */
public class JoinCountDownLatchCase {

    public static void main(String[] args) throws InterruptedException {
        /**
         * 需求：解析Excel中多个sheet数据，使用多线程每个线程解析一个sheet数据，所有sheet解析完成，程序提示解析完成
         * 上面需求实现主线程等待所有线程完成sheet的解析操作（使用join实现）
         *
         * join用于让当前执行线程等待join线程执行结束。
         * 其实现原理是不停检查,join线程是否存活，如果join线程存活则让当前线程永远等待。
         */

        Thread parser1 = new Thread(() -> {
        });

        Thread parser2 = new Thread(() -> System.out.println("parser2 finish"));

        parser1.start();
        parser2.start();
        parser1.join();
        parser2.join();

        System.out.println("all parser finish");
    }
}
