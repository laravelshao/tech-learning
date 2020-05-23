package com.laravelshao.learning.pattern.structural.decorator;

/**
 * 装饰模式测试
 *
 * @author qinghua.shao
 * @date 2020/5/23
 * @since 1.0.0
 */
public class DecoratorTest {

    public static void main(String[] args) {
        Component c = new ConcreteComponent();
        c.operation();
        System.out.println("---------------------------------");
        Component c2 = new ConcreteDecorator(c);
        c2.operation();
    }
}


