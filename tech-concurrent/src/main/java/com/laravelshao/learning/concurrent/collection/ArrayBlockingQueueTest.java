package com.laravelshao.learning.concurrent.collection;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * ArrayBlockingQueue 测试
 *
 * @author qinghua.shao
 * @date 2022/7/23
 * @since 1.0.0
 */
public class ArrayBlockingQueueTest {

    public static void main(String[] args) throws Exception {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(5);

        queue.put("元素");
        System.out.println(queue.take());

        System.out.println(queue.size());

        Iterator<String> iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
