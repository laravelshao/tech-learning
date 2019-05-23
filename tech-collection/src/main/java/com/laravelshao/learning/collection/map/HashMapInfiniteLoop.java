package com.laravelshao.learning.collection.map;

import java.util.HashMap;

/**
 * Created by shaoqinghua on 2018/4/24.
 * HashMap多线程死循环问题测试
 */
public class HashMapInfiniteLoop {

    private static HashMap<Integer, Integer> map = new HashMap<>(2, 0.75f);

    public static void main(String[] args) {
        map.put(1, 111);

        new Thread(() -> {
            map.put(2, 222);
            System.out.println(map);
        }, "T1").start();

        new Thread(() -> {
            map.put(3, 333);
            System.out.println(map);
        }, "T2").start();
    }
}
