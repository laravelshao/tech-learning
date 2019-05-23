package com.laravelshao.learning.pattern.decorator;

/**
 * Created by shaoqinghua on 2018/7/14.
 */
public class DecoratorTest {

    public static void main(String[] args) {

        Hamburger hamburger = new ChickenBurger();
        System.out.println(hamburger.getName() + "  价钱：" + hamburger.getPrice());
        Lettuce lettuce = new Lettuce(hamburger);
        System.out.println(lettuce.getName() + "  价钱：" + lettuce.getPrice());
        Chilli chilli = new Chilli(hamburger);
        System.out.println(chilli.getName() + "  价钱：" + chilli.getPrice());
        Chilli chilli2 = new Chilli(lettuce);
        System.out.println(chilli2.getName() + "  价钱：" + chilli2.getPrice());
    }
}
