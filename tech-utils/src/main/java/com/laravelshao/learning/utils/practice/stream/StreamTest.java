package com.laravelshao.learning.utils.practice.stream;

import com.google.common.collect.Lists;
import com.laravelshao.learning.utils.model.Coupon;

import java.util.Arrays;
import java.util.Comparator;
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

    public static void main(String[] args) {

        // List中对象多属性拼接作为key
        //list2Map();

        // 排序
        //sorted();

        // 过滤指定条件数据
        filter();
    }

    /**
     * List => Map
     */
    public static void list2Map() {

        List<Coupon> couponList = Lists.newArrayList();
        couponList.add(new Coupon(1, "100001"));
        couponList.add(new Coupon(2, "200001"));
        couponList.add(new Coupon(3, "300001"));
        // 将list中对象多属性拼接作为key Map<sourceType_couponId,Coupon>
        Map<String, Coupon> couponMap = couponList.stream().collect(
                Collectors.toMap(coupon -> coupon.getSourceType() + "_" + coupon.getCouponId(), coupon -> coupon));
        System.out.println(couponMap);
    }

    /**
     * 排序
     */
    public static void sorted() {

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
    public static void filter() {
        List<Integer> list = Arrays.asList(1, 123, 12, 18, 23, 19);

        // 过滤指定条件数据
        List<Integer> after = list.stream().filter(tmp -> tmp % 3 == 0).collect(Collectors.toList());
        System.out.println(after);

        //dbCouponList.stream().filter(entity -> paramCouponMap
        //        .containsKey(entity.getSourceType() + "_" + entity.getCouponActivityId()))
        //        .collect(Collectors.toList());
    }

}
