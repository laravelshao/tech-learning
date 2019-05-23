package com.laravelshao.learning.collection.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by LaravelShao on 2017/12/14.
 */
public class HashMapTest {

    public static void main(String[] args) {
        /**
         * V put(K key,V value) 添加元素
         * void clear() 移除所有键值对元素
         * V remove(Object key) 根据键删除键值对元素，并把值返回
         * boolean containsKey(Object key) 判断集合是否包含指定的键
         * boolean containsValue(Object value) 判断集合是否包含指定的值
         * boolean isEmpty() 判断集合是否为空
         * Set<Map.Entry<K,V>> entrySet() 获取键值对对象的集合
         * V get(Object key) 根据键获取值
         * Set<K> keySet() 获取集合中所有键的集合
         * Collection<V> values() 获取集合中所有值的集合
         * int size() 返回集合中的键值对的对数
         */

        //遍历Map集合

        Map map = new HashMap();

        map.put("aaa", "bbb");
        map.put("ccc", "ddd");
        map.put("eee", "fff");
        map.put("ggg", "hhh");

        /**
         * 方式1 使用keySet获取所有键的Set集合
         */
        Set set = map.keySet();
        for (Object obj : set) {
            System.out.println(obj + "-----" + map.get(obj));
        }
        System.out.println("===================================");

        /**
         * 方式2 使用EntrySet获取所有键值对的Set集合
         */
        Set entrySet = map.entrySet();
        for (Iterator it = entrySet.iterator(); it.hasNext(); ) {
            Map.Entry entry = (Map.Entry) it.next();
            System.out.println(entry.getKey() + "-----" + entry.getValue());
        }

    }
}
