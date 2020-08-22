package com.laravelshao.learning.jdk;

import java.math.BigDecimal;

/**
 * @author qinghua.shao
 * @date 2020/7/28
 * @since 1.0.0
 */
public class Sku {

    public Sku() {
    }

    public Sku(BigDecimal activityPrice) {
        this.activityPrice = activityPrice;
    }

    /*** 活动价格 */
    private BigDecimal activityPrice;

    public BigDecimal getActivityPrice() {
        return activityPrice;
    }

    public void setActivityPrice(BigDecimal activityPrice) {
        this.activityPrice = activityPrice;
    }
}
