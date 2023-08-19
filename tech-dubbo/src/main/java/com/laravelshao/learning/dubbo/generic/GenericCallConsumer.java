package com.laravelshao.learning.dubbo.generic;


import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

/**
 * 泛化调用
 *
 * @author laravel.shao
 * @date 2023/8/18
 */
public class GenericCallConsumer {

    private static GenericService genericService;

    public static void main(String[] args) throws Exception {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("generic-call-consumer");
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("zookeeper://127.0.0.1:2181");
        ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setInterface("org.apache.dubbo.samples.generic.call.api.HelloService");
        applicationConfig.setRegistry(registryConfig);
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setGeneric(true);
        referenceConfig.setAsync(true);
        referenceConfig.setTimeout(7000);

        genericService = referenceConfig.get();
        invokeSayHello();
        invokeSayHelloAsync();
        invokeAsyncSayHelloAsync();
        invokeAsyncSayHello();
//        invokeSayHelloAsyncComplex();
        asyncInvokeSayHelloAsyncComplex();
//        invokeSayHelloAsyncGenericComplex();
        asyncInvokeSayHelloAsyncGenericComplex();
    }

    public static void invokeSayHello() throws InterruptedException {
        Object result = genericService.$invoke("sayHello", new String[]{"java.lang.String"}, new Object[]{"world"});
        CountDownLatch latch = new CountDownLatch(1);

        CompletableFuture<String> future = RpcContext.getContext().getCompletableFuture();
        future.whenComplete((value, t) -> {
            System.err.println("invokeSayHello(whenComplete): " + value);
            latch.countDown();
        });

        System.err.println("invokeSayHello(return): " + result);
        latch.await();
    }

    public static void invokeSayHelloAsync() throws InterruptedException {
        Object result = genericService.$invoke("sayHelloAsync", new String[]{"java.lang.String"}, new Object[]{"world"});
        CountDownLatch latch = new CountDownLatch(1);

        CompletableFuture<String> future = RpcContext.getContext().getCompletableFuture();
        future.whenComplete((value, t) -> {
            System.err.println("invokeSayHelloAsync(whenComplete): " + value);
            latch.countDown();
        });

        System.err.println("invokeSayHelloAsync(return): " + result);
        latch.await();
    }

    public static void invokeAsyncSayHelloAsync() throws Exception {
        CompletableFuture<Object> future = genericService.$invokeAsync("sayHelloAsync",
                new String[]{"java.lang.String"}, new Object[]{"world"});
        CountDownLatch latch = new CountDownLatch(1);
        future.whenComplete((value, t) -> {
            System.err.println("invokeAsyncSayHelloAsync(whenComplete): " + value);
            latch.countDown();
        });
        latch.await();
    }

    public static void invokeAsyncSayHello() throws Exception {
        CompletableFuture<Object> future = genericService.$invokeAsync("sayHello",
                new String[]{"java.lang.String"}, new Object[]{"world"});
        CountDownLatch latch = new CountDownLatch(1);
        future.whenComplete((value, t) -> {
            System.err.println("invokeAsyncSayHello(whenComplete): " + value);
            latch.countDown();
        });
        latch.await();
    }

    public static void invokeSayHelloAsyncComplex() throws Exception {
        Object result = genericService.$invoke("sayHelloAsyncComplex", new String[]{"java.lang.String"},
                new Object[]{"world"});
        CountDownLatch latch = new CountDownLatch(1);

        CompletableFuture<String> future = RpcContext.getContext().getCompletableFuture();
        future.whenComplete((value, t) -> {
            System.err.println("invokeSayHelloAsyncComplex(whenComplete): " + value);
            latch.countDown();
        });

        System.err.println("invokeSayHelloAsync(return): " + result);
        latch.await();
    }

    public static void asyncInvokeSayHelloAsyncComplex() throws Exception {
        CompletableFuture<Object> future = genericService.$invokeAsync("sayHelloAsyncComplex",
                new String[]{"java.lang.String"}, new Object[]{"world"});
        CountDownLatch latch = new CountDownLatch(1);

        future.whenComplete((value, t) -> {
            System.err.println("asyncInvokeSayHelloAsyncComplex(whenComplete): " + value);
            latch.countDown();
        });

        latch.await();
    }

    public static void invokeSayHelloAsyncGenericComplex() throws Exception {
        Object result = genericService.$invoke("sayHelloAsyncGenericComplex", new String[]{"java.lang.String"},
                new Object[]{"world"});
        CountDownLatch latch = new CountDownLatch(1);

        CompletableFuture<String> future = RpcContext.getContext().getCompletableFuture();
        future.whenComplete((value, t) -> {
            System.err.println("invokeSayHelloAsyncGenericComplex(whenComplete): " + value);
            latch.countDown();
        });

        System.err.println("invokeSayHelloAsyncGenericComplex(return): " + result);
        latch.await();
    }

    public static void asyncInvokeSayHelloAsyncGenericComplex() throws Exception {
        CompletableFuture<Object> future = genericService.$invokeAsync("sayHelloAsyncGenericComplex",
                new String[]{"java.lang.String"}, new Object[]{"world"});
        CountDownLatch latch = new CountDownLatch(1);

        future.whenComplete((value, t) -> {
            System.err.println("asyncInvokeSayHelloAsyncGenericComplex(whenComplete): " + value);
            latch.countDown();
        });

        latch.await();
    }
}
