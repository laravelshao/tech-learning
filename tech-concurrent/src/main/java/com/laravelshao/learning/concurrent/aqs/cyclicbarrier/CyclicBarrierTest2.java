package com.laravelshao.learning.concurrent.aqs.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier和CountDownLatch的区别
 * CountDownLatch的计数器只能使用一次，而CyclicBarrier的计数器可以使用reset()方法重置。
 * 所以CyclicBarrier能处理更为复杂的业务场景。例如，如果计算发生错误，可以重置计数器，并让线程重新执行一次。
 *
 * <p>CyclicBarrier还提供其他有用的方法，
 * getNumberWaiting()：获取CyclicBarrier阻塞的线程数量
 * isBroken()：获取阻塞的线程是否被中断
 *
 * @author qinghua.shao
 * @date 2018/5/19
 * @since 1.0.0
 */
public class CyclicBarrierTest2 {

    static CyclicBarrier c = new CyclicBarrier(2);

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        Thread thread = new Thread(() -> {
            try {
                c.await();
            } catch (Exception e) {
            }
        });
        thread.start();
        thread.interrupt();
        try {
            c.await();
        } catch (Exception e) {
            System.out.println(c.isBroken());
        }
    }

}
