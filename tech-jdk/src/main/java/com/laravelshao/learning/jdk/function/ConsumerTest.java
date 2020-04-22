package com.laravelshao.learning.jdk.function;

import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Consumer 测试
 *
 * @author qinghua.shao
 * @date 2020/4/22
 * @since 1.0.0
 */
public class ConsumerTest {

    public static void main(String[] args) {

        // 1.使用consumer匿名内部类实现
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        Stream<String> stream = Stream.of("aaa", "bbb", "ddd", "ccc", "fff");
        stream.forEach(consumer);

        System.out.println("====================");

        // 2.使用lambda表达式，forEach方法需要的就是一个Consumer接口
        stream = Stream.of("aaa", "bbb", "ddd", "ccc", "fff");
        Consumer<String> consumer1 = (s) -> System.out.println(s); // lambda表达式返回的就是一个Consumer接口
        stream.forEach(consumer1);
        //更直接的方式
        //stream.forEach((s) -> System.out.println(s));
        System.out.println("====================");

        // 3.使用方法引用，方法引用也是一个consumer
        stream = Stream.of("aaa", "bbb", "ddd", "ccc", "fff");
        Consumer consumer2 = System.out::println;
        stream.forEach(consumer);
        //更直接的方式
        //stream.forEach(System.out::println);
    }
}
