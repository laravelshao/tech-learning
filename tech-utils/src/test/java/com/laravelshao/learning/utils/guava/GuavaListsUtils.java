package com.laravelshao.learning.utils.guava;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author qinghua.shao
 * @date 2020/12/31
 * @since 1.0.0
 */
public class GuavaListsUtils {

    /**
     * 列表分组
     */
    @Test
    public void partition() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        // 列表分组
        List<List<Integer>> lists = Lists.partition(list, 5);
        System.out.println(lists);
    }
}
