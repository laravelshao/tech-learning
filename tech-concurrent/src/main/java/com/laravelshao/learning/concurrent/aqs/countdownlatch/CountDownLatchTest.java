package com.laravelshao.learning.concurrent.aqs.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 测试
 *
 * @author qinghua.shao
 * @date 2018/5/19
 * @since 1.0.0
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws Exception {

        CountDownLatch countDownLatch = new CountDownLatch(2);

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000L);
                    System.out.println("线程1休眠2秒......");
                    Thread.sleep(1000L);
                    System.out.println("线程1准备执行countDown操作......");
                    countDownLatch.countDown();
                    System.out.println("线程1完成执行countDown操作......");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000L);
                    System.out.println("线程2休眠2秒......");
                    Thread.sleep(1000L);
                    System.out.println("线程2准备执行countDown操作......");
                    countDownLatch.countDown();
                    System.out.println("线程2完成执行countDown操作......");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();

        System.out.println("main线程执行countDownLatch的await方法，将会阻塞等待......");
        countDownLatch.await();
        System.out.println("所有线程执行完成，main线程的await等待阻塞结束......");
    }
}
