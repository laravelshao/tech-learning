package com.laravelshao.learning.pattern.decorator;

/**
 * 汉堡基类(被装饰者)
 * Created by shaoqinghua on 2018/7/14.
 */
public abstract class Hamburger {

    protected String name;

    public String getName() {
        return name;
    }

    public abstract double getPrice();
}
