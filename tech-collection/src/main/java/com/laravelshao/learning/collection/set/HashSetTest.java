package com.laravelshao.learning.collection.set;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by LaravelShao on 2017/12/13.
 */
public class HashSetTest {

    public static void main(String[] args) {
        // 创建集合对象
        HashSet set = new HashSet();

        // 添加元素
        set.add("aaa");
        set.add("aaa");
        set.add("bbb");
        set.add("ccc");
        set.add("ccc");
        set.add("ddd");

        // 使用Iterator遍历
        for (Iterator it = set.iterator(); it.hasNext(); ) {
            System.out.println(it.next());
        }
        System.out.println("================================");

        /**
         * 使用HashSet存储自定义对象 该对象所属的类一定要复写Object类中的hashCode和equals方法
         */
        // 创建集合对象
        HashSet set2 = new HashSet();

        // 添加Person对象到集合中(需要)
        set2.add(new Person("zhangsan", 12));
        set2.add(new Person("lisi", 22));
        set2.add(new Person("lisi", 22));
        set2.add(new Person("wangwu", 29));
        set2.add(new Person("zhaoliu", 32));
        set2.add(new Person("zhaoliu", 32));
        set2.add(new Person("tianqi", 35));

        // 遍历
        for (Iterator it = set2.iterator(); it.hasNext(); ) {
            System.out.println(it.next());
        }

    }
}
