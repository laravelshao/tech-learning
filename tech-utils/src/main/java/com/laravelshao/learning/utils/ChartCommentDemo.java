package com.laravelshao.learning.utils;

/**
 * @author qinghua.shao
 * @date 2019/11/6
 * @since 1.0.0
 */
public class ChartCommentDemo {

    public static void main(String[] args) {

    }

    /**
     * 构建优惠券使用条件
     * +-----------------------+-------------------+---------------------------+--------------+------------------------+
     * | 券来源类型(sourceType) | 券类型(couponType) | 解释                       | 前端展示(左边)| 前端展示(右边)          |
     * +-----------------------+-------------------+---------------------------+--------------+------------------------+
     * |                        代金券(0)           满减，满多少元减多少元         ￥50            满XX元， 部分商品可用
     * | 到家券(0)               折扣券(1)           满折，满多少元打多少折         9.9折           满XX元， 部分商品可用
     * |                        兑换券(2)           满兑换，满多少元兑换适配商品一件 暂不支持
     * +-----------------------+-------------------+---------------------------+--------------+------------------------+
     * | 微信券(1)              代金券(0)            满减，满多少元减多少元         ￥50            满XX元， 部分商品可用
     * +-----------------------+-------------------+---------------------------+--------------+------------------------+
     * |                        0                   满件减，满多少件减多少元       ￥50            满xx件，部分商品可用
     * |                        1                   满件折，满多少件打多少折       9.9折           满xx件，部分商品可用
     * |                        6                   满减，满x元减y元              ￥50            满XX元， 部分商品可用
     * | 扫码购券(2)             10                  组合减，组合商品减多少元       ￥50            组合减，部分商品可用
     * |                        11                  组合折，组合商品打多少折       9.9折           组合折，部分商品可用
     * |                        12                  满件享，满多少件享多少元       三件100元        部分商品可用
     * |                        十三                 满件享，满多少件享多少元       ￥50            满XX元， 部分商品可用
     * +-----------------------+-------------------+---------------------------+--------------+------------------------+
     */
    private void chartComment() {
    }
}
