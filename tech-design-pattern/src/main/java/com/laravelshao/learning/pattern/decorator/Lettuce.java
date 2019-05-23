package com.laravelshao.learning.pattern.decorator;

/**
 * 生菜配料实现类
 * Created by shaoqinghua on 2018/7/14.
 */
public class Lettuce extends CondimentDecorator {

    Hamburger hamburger;

    public Lettuce(Hamburger hamburger) {
        this.hamburger = hamburger;
    }

    @Override
    public String getName() {
        return hamburger.getName() + " 加生菜";
    }

    @Override
    public double getPrice() {
        return hamburger.getPrice() + 1.5;
    }
}
