package com.laravelshao.learning.pattern.behavioral.strategy.pay;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author shaoqinghua
 * @date 2018/12/31
 * @description
 */
public class PayTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        ctx.start();

        Card.cardMap.get(Constant.CREDIT_CARD).pay(1000.00);
        Card.cardMap.get(Constant.DEBIT_CARD).pay(2500.00);
    }
}
