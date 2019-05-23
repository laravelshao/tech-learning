package com.laravelshao.learning.collection;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by LaravelShao on 2017/12/13.
 */
public class CollectionTest {

    public static void main(String[] args) {
        /**
         * boolean add(E e)：添加元素
         * boolean addAll(Collection<? extends E> c)：添加一个集合元素
         * void clear()：移除所有元素（元素清空，集合对象依然存在）
         * boolean remove(Object o)：移除一个元素
         * boolean removeAll(Collection<?> c)：移除一个集合的元素（只要有一个元素被移除，就返回true）
         * boolean contains(Object o)：判断集合是否包含指定元素，底层依赖的是equals()方法
         * boolean containsAll(Collection<?> c)：判断集合中是否包含指定的集合元素（只有包含所有的元素，才算包含）
         * boolean isEmpty()：判断集合是否为空（元素为空，并不是对象为空）
         * Iterator<E> iterator()：迭代器，集合的专用遍历方式
         *      1.迭代器遍历集合时，一次判断对应一次取出，不要一次判断对应多次取出会抛出异常
         *      2.使用迭代器或foreach遍历集合时，不要使用集合自身的增删方法对集合进行修改操作，会发生并发修改异常
         *      3.使用迭代器遍历集合时，要删除集合中元素使用Iterator接口中的remove方法删除
         * int size()：元素个数
         * boolean retainAll(Collection<?> c)：获取两个集合的交集元素
         * Object[] toArray()：将集合转换为数组
         */

        //Collection的遍历方式

        Collection coll = new ArrayList();
        coll.add("aaa");
        coll.add("bbb");
        coll.add("ccc");

        //1.使用迭代器遍历集合
        //Iterator it = coll.iterator();
        //while (it.hasNext()) {
        //    System.out.println(it.next());
        //}

        //2.普通for循环遍历集合
        //for (Iterator it = coll.iterator(); it.hasNext(); ) {
        //    System.out.println(it.next());
        //}

        //3.foreach遍历集合
        for (Object obj : coll) {
            System.out.println(obj);
        }

    }


}
