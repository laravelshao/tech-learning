package com.laravelshao.learning.pattern.strategy.duck;

/**
 * 不会飞行行为实现
 * Created by shaoqinghua on 2018/7/14.
 */
public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("不会飞行");
    }
}
