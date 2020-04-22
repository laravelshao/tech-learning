package com.laravelshao.learning.jdk.function;

import java.util.function.Predicate;

/**
 * Predicate 测试
 *
 * @author qinghua.shao
 * @date 2020/4/22
 * @since 1.0.0
 */
public class PredicateTest {

    public static void main(String[] args) {

        Predicate<Integer> biggerThan6 = x -> x > 6;
        Predicate<Integer> lessThan3 = x -> x < 3;
        Predicate<Integer> lessThan9 = x -> x < 9;
        // 7比6大为true，取反 => false
        System.out.println("Predicate negate => " + biggerThan6.negate().test(7));
        // 7比6大 => true
        System.out.println("Predicate test() => " + biggerThan6.test(7));
        // 8比6大且比9小 => true
        System.out.println("Predicate and => " + biggerThan6.and(lessThan9).test(8));
        // 1比3小，满足一种条件 => true
        System.out.println("Predicate or => " + biggerThan6.or(lessThan3).test(1));
        // 静态方法，判定是否相等
        System.out.println("Predicate isEqual false => " + Predicate.isEqual("test").test("num"));
        System.out.println("Predicate isEqual true => " + Predicate.isEqual("num").test("num"));

    }
}
