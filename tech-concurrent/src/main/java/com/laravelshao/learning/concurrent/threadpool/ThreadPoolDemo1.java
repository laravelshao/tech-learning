package com.laravelshao.learning.concurrent.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

import static java.util.concurrent.TimeUnit.DAYS;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * 线程池案例(商城首页接口执行时间过长，使用线程池并行处理组装)
 *
 * <p>{@link ThreadPoolExecutor} 结合 {@link CountDownLatch}
 *
 * @author shaoqinghua
 * @date 2019/4/13
 * @description
 */
public class ThreadPoolDemo1 {

    /**
     * 首页执行线程池
     */
    private final static ThreadPoolExecutor HOME_PAGE_THREAD_POOL =
            new ThreadPoolExecutor(2, 4, 1L, DAYS, new LinkedBlockingQueue<>(100),
                    new ThreadFactoryBuilder().setNameFormat("首页执行线程" + "-%d").build());

    /**
     * 首页
     *
     * @return
     */
    public HomePageRsp homePage() {

        HomePageRsp homePageRsp = new HomePageRsp();
        CountDownLatch countDownLatch = new CountDownLatch(2);

        // 线程池并行执行组装逻辑
        HOME_PAGE_THREAD_POOL.execute(() -> handleModule1(homePageRsp, countDownLatch));
        HOME_PAGE_THREAD_POOL.execute(() -> handleModule2(homePageRsp, countDownLatch));
        try {
            countDownLatch.await(3, SECONDS);
        } catch (InterruptedException ex) {
            // 3秒未执行完成抛出中断异常
        }

        return homePageRsp;
    }


    /**
     * 处理模块1
     *
     * @param homePageRsp
     * @param countDownLatch
     */
    private void handleModule1(HomePageRsp homePageRsp, CountDownLatch countDownLatch) {
        try {
            // 模块1组装逻辑
        } catch (Exception ex) {

        } finally {
            countDownLatch.countDown();
        }
    }


    /**
     * 处理模块2
     *
     * @param homePageRsp
     * @param countDownLatch
     */
    private void handleModule2(HomePageRsp homePageRsp, CountDownLatch countDownLatch) {
        try {
            // 模块2组装逻辑
        } catch (Exception ex) {

        } finally {
            countDownLatch.countDown();
        }
    }

    public class HomePageRsp {

    }
}
