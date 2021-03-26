package com.laravelshao.learning.jdk;

/**
 * @author qinghua.shao
 * @date 2021/3/26
 * @since 1.0.0
 */
public class Goods {

    private Long goodsId;

    private Long skuId;

    public Goods() {
    }

    public Goods(Long goodsId, Long skuId) {
        this.goodsId = goodsId;
        this.skuId = skuId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }


}
