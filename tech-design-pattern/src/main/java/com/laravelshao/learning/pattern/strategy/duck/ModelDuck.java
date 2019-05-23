package com.laravelshao.learning.pattern.strategy.duck;

/**
 * Created by shaoqinghua on 2018/7/14.
 */
public class ModelDuck extends Duck {

    public ModelDuck() {
        flyBehavior = new FlyNoWay();
        quackBehavior = new MuteQuack();
    }

    @Override
    public void display() {
        System.out.println("模型鸭");
    }
}
