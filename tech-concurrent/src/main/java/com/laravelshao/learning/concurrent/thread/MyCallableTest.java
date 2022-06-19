package com.laravelshao.learning.concurrent.thread;

import java.util.concurrent.*;

/**
 * 实现多线程方式3：实现Callable接口，实现call方法
 */
public class MyCallableTest implements Callable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        MyCallableTest callable = new MyCallableTest();
        Future<String> future = executorService.submit(callable);
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


