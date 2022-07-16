package com.laravelshao.learning.concurrent.aqs.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by shaoqinghua on 2018/5/19.
 */
public class CountDownLatchCase {

    /**
     * 构造函数中的参数表示需要执行2次countDown方法才能释放锁
     */
    static CountDownLatch c = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        /**
         * 需求：解析Excel中多个sheet数据，使用多线程每个线程解析一个sheet数据，所有sheet解析完成，程序提示解析完成
         * 上面需求实现主线程等待所有线程完成sheet的解析操作（使用CountDownLatch实现）
         */

        new Thread(() -> {
            System.out.println(1);
            c.countDown();
            System.out.println(2);
            c.countDown();
        }).start();

        //线程等待
        c.await();
    }
}
