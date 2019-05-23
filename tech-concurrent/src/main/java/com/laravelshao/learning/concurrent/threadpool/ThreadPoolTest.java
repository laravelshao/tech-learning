package com.laravelshao.learning.concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author shaoqinghua
 * @date 2019/2/16
 * @description
 */
public class ThreadPoolTest {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        //Executors.newScheduledThreadPool()
    }
}
