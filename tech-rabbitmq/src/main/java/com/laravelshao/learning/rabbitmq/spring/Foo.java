package com.laravelshao.learning.rabbitmq.spring;

/**
 * 消费者
 */
public class Foo {

    /**
     * 消费者执行方法
     *
     * @param foo
     */
    public void listen(String foo) {
        System.out.println("消费者： " + foo);
    }
}