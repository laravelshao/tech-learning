package com.laravelshao.learning.pattern.strategy.duck;

/**
 * 定义鸣叫行为接口
 * Created by shaoqinghua on 2018/7/14.
 */
public interface QuackBehavior {

    /**
     * 提供鸣叫方法
     */
    void quack();
}
