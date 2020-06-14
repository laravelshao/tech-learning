package com.laravelshao.learning.concurrent.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author qinghua.shao
 * @date 2020/6/14
 * @since 1.0.0
 */
public class FutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Searcher searcher = new Searcher() {
            @Override
            public String search(String target) throws InterruptedException {
                Thread.sleep(3000);
                return "查询到结果了......";
            }
        };

        // Future 结合 #submit()
        Callable<String> task = () -> searcher.search("hello");
        Future<String> future = executor.submit(task);
        doOtherThings(); // 先执行其他任务
        String result = future.get();// 获取Future执行结果，如果为执行完将阻塞
        System.out.println(result);

        System.out.println("====================");

        // FutureTask
        FutureTask<String> futureTask = new FutureTask<>(task);
        executor.execute(futureTask);
        doOtherThings(); // 先执行其他任务
        String taskResult = futureTask.get();
        System.out.println(taskResult);
    }


    private static void doOtherThings() {
        System.out.println("先执行其他任务......");
    }

    interface Searcher {
        String search(String target) throws InterruptedException;
    }
}
