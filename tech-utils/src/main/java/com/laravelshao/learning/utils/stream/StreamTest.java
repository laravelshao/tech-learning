package com.laravelshao.learning.utils.stream;

import com.google.common.collect.Lists;
import com.laravelshao.learning.utils.model.Coupon;

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

        List<Coupon> couponList = Lists.newArrayList();
        couponList.add(new Coupon(1, "100001"));
        couponList.add(new Coupon(2, "200001"));
        couponList.add(new Coupon(3, "300001"));

        // 将list中对象多属性拼接作为key Map<sourceType_couponId,Coupon>
        Map<String, Coupon> couponMap = couponList.stream().collect(
                Collectors.toMap(coupon -> coupon.getSourceType() + "_" + coupon.getCouponId(), coupon -> coupon));
        System.out.println(couponMap);

        // 过滤指定条件数据
        //dbCouponList.stream().filter(entity -> paramCouponMap
        //        .containsKey(entity.getSourceType() + "_" + entity.getCouponActivityId()))
        //        .collect(Collectors.toList());
    }
}
