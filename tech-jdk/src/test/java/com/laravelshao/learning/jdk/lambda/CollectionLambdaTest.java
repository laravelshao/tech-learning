package com.laravelshao.learning.jdk.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * Collection Lambda 测试
 *
 * @author qinghua.shao
 * @date 2021/5/5
 * @since 1.0.0
 */
public class CollectionLambdaTest {

    /**
     * 遍历
     */
    @Test
    public void forEach() {

        // 使用增强for循环遍历
        List<String> list1 = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        for (String str : list1) {
            if (str.length() > 3)
                System.out.println("list1: " + str);
        }

        // 使用forEach()结合匿名内部类遍历
        List<String> list2 = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        list2.forEach(new Consumer<String>() {
            @Override
            public void accept(String str) {
                if (str.length() > 3)
                    System.out.println("list2: " + str);
            }
        });

        // 使用forEach()结合Lambda表达式遍历
        List<String> list3 = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        list3.forEach(str -> {
            if (str.length() > 3)
                System.out.println("list3: " + str);
        });
    }

    /**
     * 删除满足条件的元素
     */
    @Test
    public void removeIf() {

        // 使用迭代器删除列表元素
        List<String> list1 = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        System.out.println("list1: " + list1);
        Iterator<String> it = list1.iterator();
        while (it.hasNext()) {
            if (it.next().length() > 3) // 删除长度大于3的元素
                it.remove();
        }
        System.out.println("list1: " + list1);

        // 使用removeIf()结合匿名名内部类实现
        List<String> list2 = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        System.out.println("list2: " + list2);
        list2.removeIf(new Predicate<String>() { // 删除长度大于3的元素
            @Override
            public boolean test(String str) {
                return str.length() > 3;
            }
        });
        System.out.println("list2: " + list2);

        // 使用removeIf()结合Lambda表达式实现
        List<String> list3 = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        System.out.println("list3: " + list3);
        list3.removeIf(str -> str.length() > 3); // 删除长度大于3的元素
        System.out.println("list3: " + list3);
    }

    /**
     * 针对每个元素执行操作
     */
    @Test
    public void replaceAll() {

        // 使用下标实现元素替换
        List<String> list1 = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        System.out.println("list1: " + list1);
        for (int i = 0; i < list1.size(); i++) {
            String str = list1.get(i);
            if (str.length() > 3)
                list1.set(i, str.toUpperCase());
        }
        System.out.println("list1: " + list1);

        // 使用匿名内部类实现
        List<String> list2 = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        System.out.println("list2: " + list2);
        list2.replaceAll(new UnaryOperator<String>() {
            @Override
            public String apply(String str) {
                if (str.length() > 3)
                    return str.toUpperCase();
                return str;
            }
        });
        System.out.println("list2: " + list2);

        // 使用Lambda表达式实现
        List<String> list3 = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        System.out.println("list3: " + list3);
        list3.replaceAll(str -> {
            if (str.length() > 3)
                return str.toUpperCase();
            return str;
        });
        System.out.println("list3: " + list3);
    }

    /**
     * 排序
     */
    @Test
    public void sort() {

        // Collections.sort()方法
        List<String> list1 = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        System.out.println("list1: " + list1);
        Collections.sort(list1, new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                return str1.length() - str2.length();
            }
        });
        System.out.println("list1: " + list1);

        // List.sort()方法结合Lambda表达式
        List<String> list2 = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        System.out.println("list2: " + list2);
        list2.sort((str1, str2) -> str1.length() - str2.length());
        System.out.println("list2: " + list2);
    }
}
