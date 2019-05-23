package com.laravelshao.learning.pattern.decorator;

/**
 * 定义鸡腿堡实现类(被装饰类的基础状态)
 * Created by shaoqinghua on 2018/7/14.
 */
public class ChickenBurger extends Hamburger {

    public ChickenBurger() {
        name = "鸡腿堡";
    }

    @Override
    public double getPrice() {
        return 10;
    }
}
