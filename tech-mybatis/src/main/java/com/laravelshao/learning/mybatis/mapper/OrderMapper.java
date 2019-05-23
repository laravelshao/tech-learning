package com.laravelshao.learning.mybatis.mapper;

import com.laravelshao.learning.mybatis.pojo.Order;
import com.laravelshao.learning.mybatis.pojo.OrderUser;

/**
 * Created by shaoqinghua on 2018/7/21.
 */
public interface OrderMapper {

    /**
     * 根据订单号查询订单信息以及下单人信息
     *
     * @param orderNumber
     * @return
     */
    OrderUser queryOrderAndUserByOrderNumber(String orderNumber);

    /**
     * 根据订单号查询订单信息以及下单人信息
     *
     * @param orderNumber
     * @return
     */
    Order queryOrderAndUserByOrderNumber2(String orderNumber);

    /**
     * 测试延迟加载
     * 根据订单号查询订单信息以及下单人信息
     *
     * @param orderNumber
     * @return
     */
    Order lazyQueryOrderAndUserByOrderNumber(String orderNumber);

    /**
     * 一对多查询，根据订单号查询订单信息以及下单人信息和订单详情
     *
     * @param orderNumber
     * @return
     */
    Order queryOrderAndUserAndOrderDetailByOrderNumber(String orderNumber);

    /**
     * 多对多查询，根据订单号查询订单信息以及下单人信息和订单详情以及商品信息
     *
     * @param orderNumber
     * @return
     */
    Order queryOrderAndUserAndOrderDetailAndItemByOrderNumber(String orderNumber);

}
