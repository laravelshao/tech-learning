package com.laravelshao.learning.question.NumSum;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 使用多线程或线程池实现从1到1000000的累加求和
 *
 * @author shaoqinghua
 * @date 2025/2/26
 */
public class NumSumTask implements Callable<Long> {
    private final int start;
    private final int end;

    public NumSumTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public static void main(String[] args) {
        int totalNumbers = 1000000;
        int numThreads = 4; // 可以根据需要调整线程数
        int chunkSize = totalNumbers / numThreads;

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        Future<Long>[] futures = new Future[numThreads];

        for (int i = 0; i < numThreads; i++) {
            int start = i * chunkSize + 1;
            int end = (i == numThreads - 1) ? totalNumbers : (i + 1) * chunkSize;
            futures[i] = executor.submit(new NumSumTask(start, end));
        }

        long totalSum = 0;
        for (int i = 0; i < numThreads; i++) {
            try {
                totalSum += futures[i].get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
        System.out.println("Total Sum: " + totalSum);
    }

    @Override
    public Long call() {
        long sum = 0;
        for (int i = start; i <= end; i++) {
            sum += i;
        }
        return sum;
    }
}