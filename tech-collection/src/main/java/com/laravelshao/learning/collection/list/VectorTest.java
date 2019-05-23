package com.laravelshao.learning.collection.list;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by LaravelShao on 2017/12/13.
 */
public class VectorTest {

    public static void main(String[] args) {
        // 创建集合对象
        Vector v = new Vector();

        // 添加方法
        v.addElement("aaa");
        v.add("bbb");
        v.add("bbb");
        v.add("ccc");

        /**
         * 使用Vector的 elements 方法可以得到一个枚举迭代器（早期迭代器）
         * Enumeration : 它是一个接口，主要用来遍历集合（Vector）
         * Enumeration这个接口被Iterator代替，并且Iterator中有remove方法
         */
        Enumeration en = v.elements();
        while (en.hasMoreElements()) {
            System.out.println(en.nextElement());
        }
    }

}
