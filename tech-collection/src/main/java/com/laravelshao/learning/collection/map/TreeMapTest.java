package com.laravelshao.learning.collection.map;



import com.laravelshao.learning.collection.set.Person;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by LaravelShao on 2017/12/14.
 */
public class TreeMapTest {

    public static void main(String[] args) {
        // 创建集合对象
        TreeMap map = new TreeMap();

        map.put("aaa", "AAAA");
        map.put("abc", "AAAA");
        map.put("Abc", "AAAA");
        map.put("zAAa", "AAAA");

        // 遍历
        Set set = map.entrySet();

        for (Iterator it = set.iterator(); it.hasNext(); ) {
            Map.Entry entry = (Map.Entry) it.next();
            System.out.println(entry.getKey() + "" + entry.getValue());
        }
        System.out.println("===================================");

        /**
         * 自定义对象作为TreeMap的key值
         */
        // 创建集合对象
        TreeMap map2 = new TreeMap();

        // 添加元素
        map2.put(new Person("张三", 21), "上海");
        map2.put(new Person("李四", 22), "杭州");
        map2.put(new Person("王五", 23), "成都");
        map2.put(new Person("赵六", 24), "厦门");

        //遍历
        Set set2 = map2.keySet();

        for (Iterator it = set2.iterator(); it.hasNext(); ) {
            Object key = it.next();
            Object value = map2.get(key);
            System.out.println(key + "-----" + value);
        }


    }
}
