package com.laravelshao.learning.concurrent.collection;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayList 测试
 *
 * @author qinghua.shao
 * @date 2018/7/17
 * @since 1.0.0
 */
public class CopyOnWriteArrayListTest {

    public static void main(String[] args) {

        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("张三");
        list.set(1, "李四");

        //list.remove(1);

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println(list);
    }
}
