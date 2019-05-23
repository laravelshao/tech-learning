package com.laravelshao.learning.pattern.strategy.duck;

/**
 * 不会叫实现
 * Created by shaoqinghua on 2018/7/14.
 */
public class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("不会叫");
    }
}
