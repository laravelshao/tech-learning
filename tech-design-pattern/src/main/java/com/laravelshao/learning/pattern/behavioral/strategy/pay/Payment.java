package com.laravelshao.learning.pattern.behavioral.strategy.pay;

/**
 * @author shaoqinghua
 * @date 2018/12/31
 * @description 支付接口
 */
public interface Payment {

    void pay(double amount);
}
