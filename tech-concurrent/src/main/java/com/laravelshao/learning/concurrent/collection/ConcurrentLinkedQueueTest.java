package com.laravelshao.learning.concurrent.collection;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * ConcurrentLinkedQueue 测试
 *
 * @author qinghua.shao
 * @date 2018/7/17
 * @since 1.0.0
 */
public class ConcurrentLinkedQueueTest {

    public static void main(String[] args) {
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

        // offer:添加一个元素并返回true
        // poll:移除并返问队列头部的元素
        // peek:返回队列头部的元素
        // size:返回队列大小
        // contains:是否包含某个元素
        // 迭代器遍历
        queue.offer("张三");
        queue.offer("李四");
        queue.offer("王五");

        System.out.println(queue.poll());
        System.out.println(queue.peek());
        System.out.println(queue.size());
        System.out.println(queue.contains("李四"));

        Iterator<String> iterator = queue.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
    }
}
