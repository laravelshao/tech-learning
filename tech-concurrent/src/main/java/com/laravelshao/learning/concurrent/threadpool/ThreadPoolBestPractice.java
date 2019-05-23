package com.laravelshao.learning.concurrent.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * 线程池最佳实践
 *
 * <p>{@link Executors} 提供创建线程池的静态方法，存在以下问题，推荐使用
 * {@link ThreadPoolExecutor} 进行创建，可以方便个性化配置。
 *
 * <ol>
 *
 * <li>{@link Executors#newFixedThreadPool} 和 {@link Executors#newSingleThreadExecutor}
 * 使用 {@link LinkedBlockingQueue} 作为阻塞队列，而其基本上等同于无界队列，
 * 导致到达核心线程数后，提交的任务一直堆积在阻塞队列中，如果任务提交速度远大于处理速度，
 * 会消耗大量的内存，甚至OOM内存溢出。</li>
 *
 * <li>{@link Executors#newCachedThreadPool} 和 {@link Executors#newScheduledThreadPool}
 * 的最大线程数都是 {@link Integer#MAX_VALUE}，如果提交任务速度远大于处理速度，
 * 将会不断创建新线程执行任务，导致耗尽CPU和内存资源，所以使用时一定要注意控制并发任务数，
 * 否则创建大量的线程可能导致严重的性能问题。</li>
 *
 * </ol>
 *
 * @author shaoqinghua
 * @date 2018/9/5
 * @description
 */
public class ThreadPoolBestPractice {

    public static void main(String[] args) {

        ThreadPoolExecutor threadPool =
                new ThreadPoolExecutor(2, 3, 1L, SECONDS, new LinkedBlockingQueue<>(50),
                        new ThreadFactoryBuilder().setNameFormat("测试线程池" + "-%d").build(), new ThreadPoolExecutor.AbortPolicy());


    }
}
