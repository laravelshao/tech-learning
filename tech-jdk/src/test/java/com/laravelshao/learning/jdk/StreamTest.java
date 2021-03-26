package com.laravelshao.learning.jdk;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Stream
 *
 * @author qinghua.shao
 * @date 2019/10/14
 * @since 1.0.0
 */
public class StreamTest {

    /**
     * List => Map(List中对象多属性拼接作为key)
     */
    @Test
    public void list2Map() {

        List<Coupon> couponList = new LinkedList<>();
        couponList.add(new Coupon(1, "100001"));
        couponList.add(new Coupon(3, "200001"));
        couponList.add(new Coupon(6, "300001"));
        couponList.add(new Coupon(2, "300001"));
        couponList.add(new Coupon(5, "300001"));

        System.out.println(couponList);
        // 将list中对象多属性拼接作为key Map<sourceType_couponId,Coupon>
        Map<String, Coupon> couponMap = couponList.stream().collect(
                Collectors.toMap(coupon -> coupon.getSourceType() + "_" + coupon.getCouponId(), coupon -> coupon));
        System.out.println(couponMap);

        System.out.println("=========");
        Map<String, String> collect1 = couponList.stream().collect(Collectors.toMap(coupon -> coupon.getSourceType() + "", coupon -> coupon.getSourceType() + "_" + coupon.getCouponId()));
        System.out.println(collect1);
    }

    /**
     * 排序
     */
    @Test
    public void sorted() {

        List<Coupon> couponList = Lists.newArrayList();
        couponList.add(new Coupon(10, "100001"));
        couponList.add(new Coupon(31, "200001"));
        couponList.add(new Coupon(17, "300001"));
        // 指定排序
        List<Coupon> sortedCouponList = couponList.stream()
                .sorted(Comparator.comparing(Coupon::getCouponId).reversed()).collect(Collectors.toList());
        System.out.println(sortedCouponList);
    }

    /**
     * 过滤指定条件数据
     */
    @Test
    public void filter() {
        List<Integer> list = Arrays.asList(1, 123, 12, 18, 23, 19);

        // 过滤指定条件数据
        List<Integer> after = list.stream().filter(tmp -> tmp % 3 == 0).collect(Collectors.toList());
        System.out.println(after);

        //dbCouponList.stream().filter(entity -> paramCouponMap
        //        .containsKey(entity.getSourceType() + "_" + entity.getCouponActivityId()))
        //        .collect(Collectors.toList());
    }

    /**
     * 分组
     */
    @Test
    public void groupingBy() {

        List<Coupon> couponList = Lists.newArrayList();
        couponList.add(new Coupon(10, "100001"));
        couponList.add(new Coupon(31, "200001"));
        couponList.add(new Coupon(17, "300001"));
        couponList.add(new Coupon(17, "300002"));

        // 按照券来源分组
        Map<Integer, List<Coupon>> sourceCouponMap = couponList.stream().collect(Collectors.groupingBy(Coupon::getSourceType));

        System.out.println(sourceCouponMap);
    }

    /**
     * 累加
     */
    @Test
    public void summarizingInt() {
        List<Integer> list = Arrays.asList(3, 7, 2, 10, 6);

        IntSummaryStatistics collect = list.stream().collect(Collectors.summarizingInt(value -> value));
        System.out.println(collect);
        System.out.println("统计集合元素的个数：" + collect.getCount());
        System.out.println("集合元素累加之和：" + collect.getSum());
        System.out.println("集合中最小值：" + collect.getMax());
        System.out.println("集合中最大值：" + collect.getMin());
        System.out.println("集合中平均值：" + collect.getAverage());
    }

    /**
     * 最大值
     */
    @Test
    public void bigDecimalMax() {

        Sku sku1 = new Sku(BigDecimal.valueOf(10.23));
        Sku sku2 = new Sku(BigDecimal.valueOf(5.4));
        Sku sku3 = new Sku(BigDecimal.valueOf(15.50));

        List<Sku> skuList = new ArrayList<>();
        skuList.add(sku1);
        skuList.add(sku2);
        skuList.add(sku3);


        BigDecimal maxExchangePrice = skuList.stream().map(Sku::getActivityPrice).max(BigDecimal::compareTo).get();
        System.out.println(maxExchangePrice);
    }

    @Test
    public void mapping() {

        List<Goods> goodsList = new ArrayList<>();
        goodsList.add(new Goods(1001L, 10011L));
        goodsList.add(new Goods(1001L, 10012L));
        goodsList.add(new Goods(1002L, 10021L));
        goodsList.add(new Goods(1003L, 10031L));
        goodsList.add(new Goods(1003L, 10032L));
        goodsList.add(new Goods(1003L, 10033L));
        goodsList.add(new Goods(1003L, 10034L));

        // 根据goodsId分组并将skuId列表转换为字符类型
        Map<Long, List<String>> goodsSkuListMap = goodsList.stream().collect(Collectors.groupingBy(Goods::getGoodsId, Collectors.mapping(goods -> String.valueOf(goods.getSkuId()), Collectors.toList())));
        System.out.println(goodsSkuListMap);
    }

}
