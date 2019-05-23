package com.laravelshao.learning.pattern.strategy.duck;

/**
 * 吱吱叫实现
 * Created by shaoqinghua on 2018/7/14.
 */
public class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("吱吱叫");
    }
}
