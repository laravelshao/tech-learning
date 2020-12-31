package com.laravelshao.learning.utils.collections;

import org.apache.commons.collections.ListUtils;
import org.junit.Test;

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

    /**
     * 集合差集
     */
    @Test
    public void removeAll() {
        List list1 = Arrays.asList(new String[]{"1", "2", "3", "4"});
        List list2 = Arrays.asList(new String[]{"1", "3"});

        // 移除指定集合元素
        System.out.println(ListUtils.removeAll(list1, list2));
    }

    /**
     * 集合交集
     */
    @Test
    public void retainAll() {
        List list1 = Arrays.asList(new String[]{"1", "2", "3", "4"});
        List list2 = Arrays.asList(new String[]{"1", "3"});

        // 集合交集
        System.out.println(ListUtils.retainAll(list1, list2));
    }

    /**
     * 移除指定元素
     */
    @Test
    public void removeIf() {
        List<String> list = new ArrayList<>();

        list.add("abc");
        list.add("abc");
        list.add("abc");
        list.add(null);
        System.out.println(list);

        // 移除为空元素
        list.removeIf(Objects::isNull);
        System.out.println(list);
    }

}
