package com.laravelshao.learning.concurrent.aqs.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore 测试
 *
 * @author qinghua.shao
 * @date 2020/7/16
 * @since 1.0.0
 */
public class SemaphoreTest {

    private static final int THREAD_COUNT = 30;

    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);

    /**
     * Semaphore：信号量，是用来控制同时访问特定资源的线程数量，它通过协调各个线程，以保证合理的使用公共资源。
     * Semaphore可以控制系统的流量，拿到信号量的线程可以进入，否则就等待。通过acquire()和release()获取和释放访问许可。
     */
    private static Semaphore s = new Semaphore(10);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            threadPool.execute(() -> {
                try {
                    s.acquire(); //获取许可证
                    System.out.println("save data");
                    Thread.sleep(1000);
                    s.release(); //释放许可证
                } catch (InterruptedException e) {
                }
            });
        }

        threadPool.shutdown();
    }

}

