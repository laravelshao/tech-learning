package com.laravelshao.learning.concurrent;

import java.util.concurrent.*;

/**
 * @author shaoqinghua
 */
public class CallableDemo implements Callable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        CallableDemo callableDemo = new CallableDemo();
        Future<String> future = executorService.submit(callableDemo);
        String res = future.get(); // 阻塞等待返回接口
        System.out.println(res);
        executorService.shutdown();
    }

    @Override
    public Object call() throws Exception {
        int a = 1;
        int b = 2;
        System.out.println(a + b);
        return "执行结果：" + (a + b);
    }
}


