package com.laravelshao.learning.jdk.lambda;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * Map Lambda 测试
 *
 * @author qinghua.shao
 * @date 2021/5/5
 * @since 1.0.0
 */
public class MapLambdaTest {

    @Test
    public void forEach() {

        // Java7以及之前遍历Map
        Map<Integer, String> map1 = new HashMap<>();
        map1.put(1, "one");
        map1.put(2, "two");
        map1.put(3, "three");
        for (Map.Entry<Integer, String> entry : map1.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
        System.out.println("==============");

        // 使用forEach()结合匿名内部类遍历Map
        Map<Integer, String> map2 = new HashMap<>();
        map2.put(1, "one");
        map2.put(2, "two");
        map2.put(3, "three");
        map2.forEach(new BiConsumer<Integer, String>() {
            @Override
            public void accept(Integer k, String v) {
                System.out.println(k + "=" + v);
            }
        });
        System.out.println("==============");

        // 使用forEach()结合Lambda表达式遍历Map
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.forEach((k, v) -> System.out.println(k + "=" + v));
    }


    /**
     * 查询指定key的值，没有则返回默认值
     */
    @Test
    public void getOrDefault() {

        // 查询Map中指定的值，不存在时使用默认值
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");

        // Java7以及之前做法
        if (map.containsKey(4)) {
            System.out.println(map.get(4));
        } else {
            System.out.println("NoValue");
        }

        // Java8使用Map.getOrDefault()
        System.out.println(map.getOrDefault(4, "NoValue"));
    }

    /**
     * 全部替换
     */
    @Test
    public void replaceAll() {

        // Java7以及之前替换所有Map中所有映射关系
        Map<Integer, String> map1 = new HashMap<>();
        map1.put(1, "one");
        map1.put(2, "two");
        map1.put(3, "three");
        System.out.println("map1: " + map1);
        for (Map.Entry<Integer, String> entry : map1.entrySet()) {
            entry.setValue(entry.getValue().toUpperCase());
        }
        System.out.println("map1: " + map1);

        // 使用replaceAll()结合匿名内部类实现
        HashMap<Integer, String> map2 = new HashMap<>();
        map2.put(1, "one");
        map2.put(2, "two");
        map2.put(3, "three");
        System.out.println("map2: " + map2);
        map2.replaceAll(new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer k, String v) {
                return v.toUpperCase();
            }
        });
        System.out.println("map2: " + map2);

        // 使用replaceAll()结合Lambda表达式实现
        Map<Integer, String> map3 = new HashMap<>();
        map3.put(1, "one");
        map3.put(2, "two");
        map3.put(3, "three");
        System.out.println("map3: " + map3);
        map3.replaceAll((k, v) -> v.toUpperCase());
        System.out.println("map3: " + map3);
    }

    /**
     * 缺失时计算(当map中不存在指定key或值为空时，调用mappingFunction，且mappingFunction执行结果不为null才填入)
     */
    @Test
    public void computeIfAbsent() {

        Map<Integer, Set<String>> map1 = new HashMap<>();
        // Java7及以前的实现方式
        if (map1.containsKey(1)) {
            map1.get(1).add("one");
        } else {
            Set<String> valueSet = new HashSet<>();
            valueSet.add("one");
            map1.put(1, valueSet);
        }
        System.out.println("map1: " + map1);

        Map<Integer, Set<String>> map2 = new HashMap<>();
        // Java8的实现方式
        map2.computeIfAbsent(1, v -> new HashSet<>()).add("yi");
        System.out.println("map1: " + map1);
    }

    /**
     * 存在时计算(map中存在key且值非null，才调用remappingFunction，如果remappingFunction执行结果为空，则删除该key，否则将计算结果替换key对应值)
     */
    @Test
    public void computeIfPresent() {

        Map<Integer, Integer> map1 = new HashMap<>();
        map1.put(1, 1000);
        System.out.println("map1: " + map1);
        // Java7及以前跟computeIfPresent()等效的代码
        if (map1.get(1) != null) {
            Integer oldValue = map1.get(1);
            Integer newValue = 1001;
            if (newValue != null) {
                map1.put(1, newValue);
            } else {
                map1.remove(1);
            }
        }
        System.out.println("map1: " + map1);
        System.out.println("===================");

        Map<Integer, Integer> map2 = new HashMap<>();
        map2.put(1, 1000);
        System.out.println("map2: " + map2);
        // Java8使用computeIfAbsent结合匿名内部类实现
        map2.computeIfPresent(1, new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return 1001;
            }
        });
        System.out.println("map2: " + map2);
        System.out.println("===================");

        Map<Integer, Integer> map3 = new HashMap<>();
        map3.put(1, 1000);
        System.out.println("map3: " + map3);
        // Java8使用computeIfAbsent结合Lambda表达式
        map3.computeIfPresent(1, (k, v) -> 1001);
        System.out.println("map3: " + map3);
    }
}
