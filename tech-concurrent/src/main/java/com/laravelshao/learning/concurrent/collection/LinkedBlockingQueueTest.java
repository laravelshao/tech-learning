package com.laravelshao.learning.concurrent.collection;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * LinkedBlockingQueue 测试
 *
 * @author qinghua.shao
 * @date 2018/7/23
 * @since 1.0.0
 */
public class LinkedBlockingQueueTest {

    public static void main(String[] args) throws Exception {

        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();

        // 写场景
        queue.put("元素");
        System.out.println(queue.take());

        // 读场景
        System.out.println(queue.size());
        Iterator<String> iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
