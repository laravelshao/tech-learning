package com.laravelshao.learning.spring.proxy.cglib;

/**
 * Created by shaoqinghua on 2017/12/18.
 */
public class OrderService {

    public void save() {
        System.out.println("保存订单成功");
    }

    public int select() {
        System.out.println("查询订单成功");
        return 10;
    }
}
