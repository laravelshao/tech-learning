package com.laravelshao.learning.concurrent.aqs.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by shaoqinghua on 2018/5/19.
 */
public class CyclicBarrierTest2 {

    /**
     * CyclicBarrier(int parties, Runnable barrierAction)：
     * 用于在线程到达屏障时，优先执行barrierAction，方便执行更复杂的业务场景
     */
    static CyclicBarrier c = new CyclicBarrier(2, new A());

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

    static class A implements Runnable {

        @Override
        public void run() {
            System.out.println(3);
        }

    }
}
