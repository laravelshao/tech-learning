package com.laravelshao.learning.spring;

import com.laravelshao.learning.spring.lifecycle.service.MyAwareService;
import com.laravelshao.learning.spring.lifecycle.service.MyService;
import com.laravelshao.learning.spring.lifecycle.service.MyStudentService;
import com.laravelshao.learning.spring.lifecycle.service.StudentService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean完整生命周期测试
 * Created by shaoqinghua on 2018/8/27.
 */
public class SpringLifecycleTest {

    @Test
    public void springBeanLifecycleTest() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        /**
         * 实现InitializingBean、DisposableBean接口方式
         */
        //StudentService studentService = (StudentService) context.getBean("studentService");
        //System.out.println(studentService);

        /**
         * XML配置init-method、destroy-method
         */
        //MyStudentService myStudentService = (MyStudentService) context.getBean("myStudentService");
        //System.out.println(myStudentService);

        /**
         * 注解配置(PostConstruct、PreDestroy)初始化及销毁方法
         */
        //MyService myService = (MyService) context.getBean("myService");
        //System.out.println(myService);

        // 实现*Aware接口
        MyAwareService myAwareService = context.getBean("myAwareService", MyAwareService.class);
        System.out.println(myAwareService);

        context.close();
    }

}
