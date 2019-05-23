package com.laravelshao.learning.pattern.factory.simple;

/**
 * 简单工厂模式(不属于23种设计模式)
 * Created by shaoqinghua on 2018/8/5.
 */
public class SimpleFactoryTest {

    public static void main(String[] args) {
        SimpleFactory factory = new SimpleFactory();
        System.out.println(factory.getMilk("蒙牛"));
        System.out.println(factory.getMilk("伊利"));
        System.out.println(factory.getMilk("特仑苏"));

    }
}
