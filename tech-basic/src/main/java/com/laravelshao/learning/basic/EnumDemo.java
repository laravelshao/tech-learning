package com.laravelshao.learning.basic;

/**
 * 枚举说明：
 * 枚举实际是 final class
 * 枚举成员修饰符为 public static final
 * values() 是 Java 编译器实现的字节码提升
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
