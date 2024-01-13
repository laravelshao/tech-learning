package com.laravelshao.learning.spring.proxy.cglib;


/**
 * @author qinghua.shao
 * @date 2017/12/18
 * @since 1.0
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
