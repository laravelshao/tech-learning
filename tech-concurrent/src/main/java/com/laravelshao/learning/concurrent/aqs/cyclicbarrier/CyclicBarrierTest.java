package com.laravelshao.learning.concurrent.aqs.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 测试
 * <p>
 * CyclicBarrier：同步屏障，让一组线程到达一个屏障（也可以叫同步点）时被阻塞，
 * 直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续运行。
 *
 * @author qinghua.shao
 * @date 2018/5/19
 * @since 1.0.0
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {

        // CyclicBarrier(int parties, Runnable barrierAction)： 用于在线程到达屏障时，优先执行barrierAction，方便执行更复杂的业务场景
        CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("所有线程都完成了自己的任务，现在可以合并结果了......");
            }
        });

        new Thread(() -> {
            try {
                System.out.println("线程1执行自己的一部分工作......");
                barrier.await();
                System.out.println("最终结果合并完成，线程1可以退出......");
            } catch (Exception e) {

            }
        }).start();

        new Thread(() -> {
            try {
                System.out.println("线程2执行自己的一部分工作......");
                barrier.await();
                System.out.println("最终结果合并完成，线程2可以退出......");
            } catch (Exception e) {

            }
        }).start();

        new Thread(() -> {
            try {
                System.out.println("线程3执行自己的一部分工作......");
                barrier.await();
                System.out.println("最终结果合并完成，线程3可以退出......");
            } catch (Exception e) {

            }
        }).start();
    }
}
