package com.laravelshao.learning.pattern.factory.abstr;

/**
 * 抽象工厂模式
 * Created by shaoqinghua on 2018/8/5.
 */
public class AbstractFactoryTest {

    public static void main(String[] args) {
        MilkFactory factory = new MilkFactory();
        System.out.println(factory.getMengNiu());
        // 新增三鹿牛奶
        System.out.println(factory.getSanLu());
    }
}
