package com.laravelshao.learning.jdk;

/**
 * 枚举说明：
 * 1.枚举实际是 final class
 * 2.枚举成员为常量，类型为当前类型，修饰符为 public static final
 * 3.values() 是 Java 编译器实现的字节码提升
 *
 * 设计：枚举可以存在抽象方法 {@link java.util.concurrent.TimeUnit}
 *
 * @author qinghua.shao
 * @date 2017/8/25
 * @since 1.0.0
 */
public class EnumDemo {

    public static void main(String[] args) {
        PriceHandleTypeEnum priceHandleTypeEnum = PriceHandleTypeEnum.byCode("1");
        System.out.println(priceHandleTypeEnum.getValue());
    }
}
