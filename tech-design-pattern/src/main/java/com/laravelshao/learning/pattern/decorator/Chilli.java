package com.laravelshao.learning.pattern.decorator;

/**
 * 辣椒配料实现类
 * Created by shaoqinghua on 2018/7/14.
 */
public class Chilli extends CondimentDecorator {

    Hamburger hamburger;

    public Chilli(Hamburger hamburger) {
        this.hamburger = hamburger;
    }

    @Override
    public String getName() {
        return hamburger.getName() + " 加辣椒";
    }

    @Override
    public double getPrice() {
        return hamburger.getPrice(); //辣椒免费
    }
}
