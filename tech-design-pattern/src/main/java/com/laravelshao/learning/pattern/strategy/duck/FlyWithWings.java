package com.laravelshao.learning.pattern.strategy.duck;

/**
 * 翅膀飞行行为实现
 * Created by shaoqinghua on 2018/7/14.
 */
public class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("使用翅膀飞行");
    }
}
