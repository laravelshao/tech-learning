package com.laravelshao.learning.collection.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by LaravelShao on 2017/12/13.
 */
public class ArrayListTest {

    public static void main(String[] args) {
        /**
         * List方法
         *
         * void add(int index, E element)：在指定的位置添加元素
         * boolean addAll(int index,Collection<? extends E> c)：在指定位置插入指定集合元素
         * E get(int index)：获取指定位置的元素
         * List<E> subList(int fromIndex,int toIndex)：返回指定索引之间的元素集合
         * ListIterator<E> listIterator()：List集合特有的迭代器 可对集合元素增删改查及正逆向遍历
         * ListIterator<E> listIterator(int index)：从集合的指定位置进行迭代
         * E remove(int index)：根据索引删除元素，返回被删除的元素
         * E set(int index, E element)：根据索引修改元素，返回 被修改的元素
         */

        //List的遍历方式

        List list = new ArrayList();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        list.add("ddd");
        list.add("eee");

        // 1.使用通用Iterator迭代器遍历
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            // 遍历同时修改则会抛出并发修改异常 ConcurrentModificationException
            //list.remove(it.next());
            System.out.println(it.next());
        }

        //// 2.使用List特有的ListIterator迭代器遍历
        //for (ListIterator it = list.listIterator(); it.hasNext(); ) {
        //    System.out.println(it.next());
        //}

        //// 3.使用foreach遍历
        //for (Object obj : list) {
        //    System.out.println(obj);
        //}

        //// 4.使用普通for循环遍历
        //for (int i = 0; i < list.size(); i++) {
        //    System.out.println(list.get(i));
        //}
    }
}
