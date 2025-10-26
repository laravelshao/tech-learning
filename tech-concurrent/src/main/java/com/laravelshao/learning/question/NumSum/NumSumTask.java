package com.laravelshao.learning.question.NumSum;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 使用多线程或线程池实现从1到1000000的累加求和
 *
 * @author qinghua.shao
 * @date 2025/2/26
 * @since 1.0.0
 */
public class NumSumTask implements Callable<Long> {

    private final int start;
    private final int end;

    public NumSumTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Long call() throws Exception {

        long sum = 0;
        for(int i = start; i <= end; i++){
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        int nums = 1000000; // 总数
        int threads = 10; // 线程数
        int batch = nums / threads; // 单批数量

        ExecutorService executorService = Executors.newFixedThreadPool(threads);
        Future<Long>[] futures = new Future[threads];

        for(int i = 0; i < threads; i++) {
            int start = i * batch + 1;
            int end = (i == threads - 1) ? nums : (i + 1) * batch;
            futures[i] = executorService.submit(new NumSumTask(start, end));
        }

        long sumTotal = 0;
        for(int i = 0; i < threads; i++) {
            sumTotal += futures[i].get();
        }

        executorService.shutdown();

        System.out.println(sumTotal);
    }
}
