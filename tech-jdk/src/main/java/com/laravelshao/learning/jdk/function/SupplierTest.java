package com.laravelshao.learning.jdk.function;

import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Supplier 测试
 *
 * @author qinghua.shao
 * @date 2020/4/22
 * @since 1.0.0
 */
public class SupplierTest {

    public static void main(String[] args) {
        // 1.使用Supplier匿名内部类实现，只有一个get方法，无参数，返回一个值
        Supplier<Integer> supplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                //返回一个随机值
                return new Random().nextInt();
            }
        };
        System.out.println(supplier.get());

        System.out.println("====================");

        // 2.使用lambda表达式
        Supplier<Integer> supplier2 = () -> new Random().nextInt();
        System.out.println(supplier2.get());
        System.out.println("====================");

        // 3.使用方法引用
        Supplier<Double> supplier3 = Math::random;
        System.out.println(supplier2.get());
        System.out.println("====================");

        // 4.Optional存在需要Supplier作为入参方法
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
        Optional<Integer> first = stream.filter(i -> i > 4).findFirst();

        // orElse，如果first中存在数，就返回这个数，如果不存在，就返回传入的数
        System.out.println(first.orElse(1));
        System.out.println(first.orElse(7));
        System.out.println("====================");

        //orElseGet，如果first中存在数，就返回这个数，如果不存在，就返回supplier返回的值
        System.out.println(first.orElseGet(supplier));
    }
}
