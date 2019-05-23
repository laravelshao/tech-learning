package com.laravelshao.learning.pattern.factory.method;

/**
 * 工厂方法模式
 * Created by shaoqinghua on 2018/8/5.
 */
public class FactoryMethodTest {

    public static void main(String[] args) {
        Factory factory = new MengNiuFactory();
        System.out.println(factory.getMilk());
    }
}
