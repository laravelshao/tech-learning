package com.laravelshao.learning.jdk;

import java.io.Serializable;
import java.util.List;

public class GoodsPolymerBO implements Serializable {
    private static final long serialVersionUID = -2471128492970618359L;

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * SKU ID 列表
     */
    private List<Long> skuIds;

    public GoodsPolymerBO() {
    }

    public GoodsPolymerBO(Long activityId, Long goodsId, List<Long> skuIds) {
        this.activityId = activityId;
        this.goodsId = goodsId;
        this.skuIds = skuIds;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public List<Long> getSkuIds() {
        return skuIds;
    }

    public void setSkuIds(List<Long> skuIds) {
        this.skuIds = skuIds;
    }
}