package com.laravelshao.learning.concurrent.aqs.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by shaoqinghua on 2018/5/19.
 */
public class CyclicBarrierTest {

    /**
     * CyclicBarrier：同步屏障，让一组线程到达一个屏障（也可以叫同步点）时被阻塞，
     * 直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续运行。
     *
     * <p>下面测试输出可能是：1 2 也可能是：2 1
     *
     * <p>将构造中参数设置为3，则主线程和子线程会永远等待，
     * 因为没有第三个线程执行await方法，即没有第三个线程到达屏障，
     * 所以之前到达屏障的两个线程都不会继续执行
     */
    static CyclicBarrier c = new CyclicBarrier(2);

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                c.await();
            } catch (Exception e) {

            }
            System.out.println(1);
        }).start();

        try {
            c.await();
        } catch (Exception e) {

        }
        System.out.println(2);
    }

}
