package com.laravelshao.learning.pattern.strategy.duck;

/**
 * Created by shaoqinghua on 2018/7/14.
 */
public abstract class Duck {

    /**
     * 为行为接口类型声明引用变量
     */
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    /**
     * 提供set方法设置行为
     *
     * @param fb
     */
    public void setFlyBehavior(FlyBehavior fb) {
        this.flyBehavior = fb;
    }

    public void setQuackBehavior(QuackBehavior qb) {
        this.quackBehavior = qb;
    }

    public Duck() {
    }

    /**
     * 委托给行为类执行具体的行为
     */
    public void performFly() {
        flyBehavior.fly();
    }

    public void performQuack() {
        quackBehavior.quack();
    }

    public void swim() {
        System.out.println("all duck can swim");
    }

    /**
     * 子类自己实现方法
     */
    public abstract void display();

}
