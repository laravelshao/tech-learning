package com.laravelshao.learning.concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池测试
 *
 * @author qinghua.shao
 * @date 2019/2/16
 * @since 1.0.0
 */
public class ThreadPoolTest {

    public static void main(String[] args) {

        // 固定线程数线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        // 单个线程线程池
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        // 可根据需要创建新线程的线程池
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        // 定时执行任务的线程池
        ExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);

        fixedThreadPool.execute(() -> System.out.println("线程任务"));

        singleThreadPool.execute(() -> System.out.println("线程任务"));

        cachedThreadPool.execute(() -> System.out.println("线程任务"));
    }
}
