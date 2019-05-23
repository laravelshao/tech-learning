package com.laravelshao.learning.collection.set;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by LaravelShao on 2017/12/13.
 */
public class TreeSetTest {

    public static void main(String[] args) {
        // 创建集合对象
        TreeSet set = new TreeSet();
        // 给集合中添加方法
        set.add("aaa");
        set.add("aaa");
        set.add("aaa");
        set.add("bbb");
        set.add("bbb");
        set.add("AAA");
        set.add("ABC");
        set.add("Abc");
        set.add("123");
        // 遍历
        for (Iterator it = set.iterator(); it.hasNext(); ) {
            System.out.println(it.next());
        }
        System.out.println("===================================");

        /**
         * TreeSet集合保存自定义对象 需要让对象所属类实现Comparable接口
         *
         * 原因：
         * 在TreeSet的底层，需要将添加到TreeSet集合中的对象强制转成Comparable类型。
         * 如果添加的对象不属于Comparable类型，那么在添加时会发生类型转换异常。
         *
         * 底层将传递的对象强转成Comparable接口的原因：因为Comparable接口是Java中规定的比较大小的接口。
         * 只要哪个类需要比较大小，就应该主动去实现Comparable接口
         */
        TreeSet set2 = new TreeSet();
        set2.add(new Person("zhansgan", 12));
        System.out.println(set2);
        System.out.println("====================================");

        /**
         * 需求：使用比较器 对字符串按照长度进行比较
         *
         * 解决：因为String类被final修饰，compareTo方法不能被重写，
         * 因此可在创建TreeSet时在构造函数中传递自定义的构造器(可使用匿名内部类实现)
         */
        TreeSet set3 = new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (!(o1 instanceof String)) {
                    throw new ClassCastException("传递的不是String类型");
                }
                if (!(o2 instanceof String)) {
                    throw new ClassCastException("传递的不是String类型");
                }

                String s1 = (String) o1;
                String s2 = (String) o2;

                int temp = s1.length() - s2.length();
                return temp == 0 ? s1.compareTo(s2) : temp;
            }
        });

        set3.add("aaa");
        set3.add("aab");
        set3.add("a");
        set3.add("AAA");
        set3.add("aa");
        set3.add("dddd");
        set3.add("aaa");
        set3.add("cccc");
        set3.add("aaa");
        set3.add("eeeee");
        set3.add("aaa");
        set3.add("MMMMMMMM");

        //遍历集合
        for (Iterator it = set3.iterator(); it.hasNext(); ) {
            System.out.println(it.next());
        }


    }
}
