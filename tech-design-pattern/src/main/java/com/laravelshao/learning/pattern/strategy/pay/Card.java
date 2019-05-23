package com.laravelshao.learning.pattern.strategy.pay;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author shaoqinghua
 * @date 2018/12/31
 * @description
 */
public abstract class Card implements Payment {

    private String name;
    private double number;

    /**
     * 记录当前抽象类型的所有支付渠道
     */
    static Map<String, Card> cardMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        cardMap.put(getType(), this);
    }

    public Card() {
    }

    public Card(String name, double number) {
        this.name = name;
        this.number = number;
    }

    @Override
    public void pay(double amount) {
        exexTransaction(amount);
    }

    /**
     * 获取支付类型
     */
    protected abstract String getType();

    /**
     * 执行交易
     *
     * @param amount
     */
    protected abstract void exexTransaction(double amount);

}
