package com.laravelshao.learning.spring;

import com.laravelshao.learning.spring.di.annotation.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by shaoqinghua on 2017/12/17.
 */
@RunWith(SpringJUnit4ClassRunner.class) //junit整合spring 使用继承开发的注解
@ContextConfiguration(locations = "classpath:applicationContext.xml")
//自动开启注解功能 自动加载核心配置文件 自动构建spring容器 自动将当前类生成bean的对象 将其放入spring容器中
public class SpringJunitTest {

    //声明要注入测试的bean
    @Autowired
    private CustomerService customerService;

    /**
     * spring整合junit测试
     */
    @Test
    public void springJunitTest() {
        customerService.save();
    }
}
