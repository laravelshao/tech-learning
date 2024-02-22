package com.laravelshao.learning.concurrent.collection;

import java.util.concurrent.SynchronousQueue;

/**
 * SynchronousQueue 测试
 *
 * @author qinghua.shao
 * @date 2023/10/21
 * @since 1.0.0
 */
public class SynchronousQueueTest {

    public static void main(String[] args) throws InterruptedException {

        SynchronousQueue<String> queue = new SynchronousQueue<>();

        // 写
        queue.put("张三");

        // 读
        queue.take();
    }
}
