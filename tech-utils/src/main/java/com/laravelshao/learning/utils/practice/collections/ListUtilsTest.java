package com.laravelshao.learning.utils.practice.collections;

import org.apache.commons.collections.ListUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author qinghua.shao
 * @date 2019/8/20
 * @since 1.0.0
 */
public class ListUtilsTest {

    public static void main(String[] args) {
        List origin = Arrays.asList(new String[]{"1", "2", "3", "4"});
        List list2 = Arrays.asList(new String[]{"1", "3"});

        //// 移除指定集合元素
        //System.out.println(ListUtils.removeAll(origin, list2));
        //// 交集
        System.out.println(ListUtils.retainAll(origin, list2));

        List<String> list = new ArrayList<>();

        list.add("abc");
        list.add("abc");
        list.add("abc");
        list.add("abc");
        System.out.println(list);

        list.removeIf(Objects::isNull);
        System.out.println(list);

    }

}
