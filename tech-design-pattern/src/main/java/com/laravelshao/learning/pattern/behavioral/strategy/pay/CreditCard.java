package com.laravelshao.learning.pattern.behavioral.strategy.pay;

import org.springframework.stereotype.Service;

/**
 * @author shaoqinghua
 * @date 2018/12/31
 * @description 信用卡支付
 */
@Service
public class CreditCard extends Card {

    @Override
    protected String getType() {
        return Constant.CREDIT_CARD;
    }

    @Override
    protected void exexTransaction(double amount) {
        System.out.println("使用支付方式:credit, 金额:[" + amount + "]");
    }
}
