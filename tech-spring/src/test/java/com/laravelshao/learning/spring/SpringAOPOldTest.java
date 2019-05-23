package com.laravelshao.learning.spring;

import com.laravelshao.learning.spring.aop.ICustomerService;
import com.laravelshao.learning.spring.aop.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by shaoqinghua on 2017/12/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-oldaop.xml")
public class SpringAOPOldTest {

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ProductService productService;

    /**
     * 传统Spring AOP测试
     */
    @Test
    public void oldAOPTest() {
        customerService.save();
        customerService.find();
        System.out.println("---------------------------");
        productService.save();
        productService.find();
    }

}
