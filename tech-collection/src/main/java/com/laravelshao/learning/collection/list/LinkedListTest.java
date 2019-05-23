package com.laravelshao.learning.collection.list;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by LaravelShao on 2017/12/13.
 */
public class LinkedListTest {
    public static void main(String[] args) {
        //LinkedList 底层是链表结构 有围绕头尾设计的操作
        LinkedList list = new LinkedList();

        //添加元素
        list.addFirst("aaa");
        list.addFirst("bbb");
        list.addFirst("ccc");
        list.addFirst("ddd");

        //遍历集合
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            System.out.println(it.next());
        }
        System.out.println("=============================");

        //移除元素
        list.removeFirst();
        System.out.println(list);

        /**
         *  模拟数据结构 由于LinkedList集合拥有针对头尾的操作，可用于模拟其他的数据结构
         *
         *  队列结构：这种结构存储的数据在容器，最先进入容器的元素，最先出去。
         *  	简单介绍：先进先出，后进后出
         *  	例如：排队买票。火车过山洞。
         *
         *  堆栈结构：这种结构存储的数据在容器，最先进入的最后出去
         *  	简单介绍：先进后出，后进先出。
         *  	例如：弹夹。Java中的栈内存。
         */

        // 创建集合对象
        LinkedList list2 = new LinkedList();

        // 添加元素
        list2.addLast("aaa");
        list2.addLast("bbb");
        list2.addLast("ccc");
        list2.addLast("ddd");
        list2.addLast("eee");

        // 模拟队列结构
        System.out.println(list2.removeFirst());
        System.out.println(list2.removeFirst());
        System.out.println(list2.removeFirst());
        System.out.println(list2.removeFirst());
        System.out.println(list2.removeFirst());

        /**
         *  结论：使用LinkedList模拟队列结构：添加和删除方法调用相反
         *  	添加使用addLast ，删除就使用removeFirst
         *  	添加使用addFirst，删除就使用removeLast
         *
         *      模拟堆栈结构：添加和删除方法调用相同
         *      添加使用addLast ，删除就使用removeLast
         *      添加使用addFirst ，删除就使用removeFirst
         */


    }
}
