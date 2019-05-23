package com.laravelshao.learning.pattern.strategy.pay;

import org.springframework.stereotype.Service;

/**
 * @author shaoqinghua
 * @date 2018/12/31
 * @description 借记卡支付
 */
@Service
public class DebitCard extends Card {

    @Override
    protected String getType() {
        return Constant.DEBIT_CARD;
    }

    @Override
    protected void exexTransaction(double amount) {
        System.out.println("使用支付方式:debit, 金额:[" + amount + "]");
    }
}
