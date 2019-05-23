package com.laravelshao.learning.mybatis.mapper;

import com.laravelshao.learning.mybatis.pojo.Order;
import com.laravelshao.learning.mybatis.pojo.OrderUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * Created by shaoqinghua on 2018/7/21.
 */
public class OrderMapperTest {

    private OrderMapper orderMapper;

    @Before
    public void setUp() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        this.orderMapper = sqlSession.getMapper(OrderMapper.class);
    }

    /**
     * 一对一查询 根据订单号查询订单信息以及下单人信息(实现一)
     */
    @Test
    public void testQueryOrderAndUserByOrderNumber() {
        OrderUser orderUser = this.orderMapper.queryOrderAndUserByOrderNumber("20140921001");
        System.out.println(orderUser);
    }

    /**
     * 一对一查询 根据订单号查询订单信息以及下单人信息(实现二)
     */
    @Test
    public void testQueryOrderAndUserByOrderNumber2() {
        Order order = this.orderMapper.queryOrderAndUserByOrderNumber2("20140921001");
        System.out.println(order);
    }

    /**
     * 一对多查询 根据订单号查询订单信息以及下单人信息和订单详情
     */
    @Test
    public void testqueryOrderAndUserAndOrderDetailByOrderNumber() {
        Order order = this.orderMapper.queryOrderAndUserAndOrderDetailByOrderNumber("20140921001");
        System.out.println(order);
    }

    /**
     * 多对多查询 根据订单号查询订单信息以及下单人信息和订单详情以及商品信息
     */
    @Test
    public void testqueryOrderAndUserAndOrderDetailAndItemByOrderNumber() {
        Order order = this.orderMapper.queryOrderAndUserAndOrderDetailAndItemByOrderNumber("20140921001");
        System.out.println(order);
    }

    @Test
    public void testLazyqueryOrderAndUserAndOrderDetailAndItemByOrderNumber() {
        Order order = this.orderMapper.lazyQueryOrderAndUserByOrderNumber("20140921001");
        System.out.println(order.getUser());
        System.out.println(order);
    }
}