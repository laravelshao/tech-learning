package com.laravelshao.learning.spring;

import com.laravelshao.learning.spring.proxy.cglib.CglibProxyFactory;
import com.laravelshao.learning.spring.proxy.cglib.OrderService;
import com.laravelshao.learning.spring.proxy.jdk.JdkProxyFactory;
import com.laravelshao.learning.spring.proxy.jdk.UserService;
import com.laravelshao.learning.spring.proxy.jdk.UserServiceImpl;
import org.junit.Test;

/**
 * Created by shaoqinghua on 2017/12/18.
 */
public class SpringProxyTest {

    /**
     * JDK动态代理测试
     */
    @Test
    public void JdkProxyTest() {
        //创建目标对象
        UserService userService = new UserServiceImpl();
        //创建工厂对象
        JdkProxyFactory jdkProxyFactory = new JdkProxyFactory(userService);
        UserService proxy = (UserService) jdkProxyFactory.getProxyObject();
        proxy.save();
        System.out.println("=========================");
        proxy.select();
    }

    /**
     * CGLIB动态代理测试
     */
    @Test
    public void CglibProxyTest() {
        //创建目标对象
        OrderService orderService = new OrderService();
        //创建工厂对象
        CglibProxyFactory cglibProxyFactory = new CglibProxyFactory(orderService);
        OrderService proxy = (OrderService) cglibProxyFactory.getProxyObject();
        proxy.save();
        System.out.println("=========================");
        proxy.select();
    }


}
