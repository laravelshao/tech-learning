package com.laravelshao.learning.pattern.strategy.duck;

/**
 * 策略模式测试
 * Created by shaoqinghua on 2018/7/14.
 */
public class DuckStrategyTest {

    public static void main(String[] args) {
        Duck duck = new ModelDuck();
        duck.performFly();
        duck.performQuack();
        duck.setFlyBehavior(new FlyWithWings());
        duck.setQuackBehavior(new NormalQuack());
        duck.performFly();
        duck.performQuack();
    }
}

