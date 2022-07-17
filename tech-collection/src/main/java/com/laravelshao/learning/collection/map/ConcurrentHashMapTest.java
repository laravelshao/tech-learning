package com.laravelshao.learning.collection.map;

import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMap 测试
 *
 * @author qinghua.shao
 * @date 2018/7/16
 * @since 1.0.0
 */
public class ConcurrentHashMapTest {

    public static void main(String[] args) {

        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("k1", "v1");

        System.out.println(map.get("k1"));
    }
}
