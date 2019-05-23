package com.laravelshao.learning.collection.map;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Created by LaravelShao on 2017/12/14.
 */
public class HashtableTest {

    public static void main(String[] args) {

        Hashtable table = new Hashtable();

        //添加元素
        table.put("aa", "bb");
        table.put("aa2", "bb2");
        table.put("aa3", "bb3");

        //取出Hashtable的所有value
        Enumeration en = table.elements();
        while (en.hasMoreElements()) {
            System.out.println(en.nextElement());
        }
        System.out.println("---------------------");

        //取出Hashtable中的所有key
        Enumeration keys = table.keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = table.get(key);
            System.out.println(key + "------" + value);
        }

    }
}
