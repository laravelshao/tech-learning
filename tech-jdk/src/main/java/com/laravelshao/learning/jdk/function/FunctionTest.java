package com.laravelshao.learning.jdk.function;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Function 测试
 *
 * <p>BiFuntion 与 Function 的不同就是传入两个参数
 *
 * @author qinghua.shao
 * @date 2020/4/21
 * @since 1.0.0
 */
public class FunctionTest {

    public static void main(String[] args) {
        Function<Integer, Integer> name = e -> e * 2;
        Function<Integer, Integer> square = e -> e * e;
        // BiFunction入参为两个参数
        BiFunction<String, String, Integer> addLength = (s, s1) -> s.length() + s1.length();
        // andThen => 先执行当前函数对象apply方法，再执行after函数对象apply方法
        int value = name.andThen(square).apply(3);
        System.out.println("Function andThen => " + value);
        // compose => 先执行before函数对象apply方法，再执行当前函数对象apply方法
        int value2 = name.compose(square).apply(3);
        System.out.println("Function compose => " + value2);
        // identity => 返回执行apply()方法输入参数的函数对象
        Object identity = Function.identity().apply("huohuo");
        System.out.println("Function identity => " + identity);
        Integer value3 = addLength.andThen(square).apply("abc", "hello");
        System.out.println("BiFunction andThen => " + value3);
    }

    /**
     * 自定义长度Function
     * 简写 => Function<String,Integer> length = s -> s.length();
     */
    public class MyLengthFunction implements Function<String, Integer> {
        @Override
        public Integer apply(String s) {
            return s.length();
        }
    }

    /**
     * 自定义两个字符串长度相加BiFunction
     * 简写 => BiFunction<String, String, Integer> addLength = (s, s1) -> s.length() + s1.length();
     */
    public class MyBiFunction implements BiFunction<String, String, Integer> {
        @Override
        public Integer apply(String s, String s2) {
            return s.length() + s2.length();
        }
    }
}
