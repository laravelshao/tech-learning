package com.laravelshao.learning.spring;

import com.laravelshao.learning.spring.di.annotation.CustomerService;
import com.laravelshao.learning.spring.di.xml.Car;
import com.laravelshao.learning.spring.di.xml.Person;
import com.laravelshao.learning.spring.ioc.UserService;
import com.laravelshao.learning.spring.ioc.UserServiceImpl;
import com.laravelshao.learning.spring.lifecycle.LifecycleBean;
import com.laravelshao.learning.spring.scope.PrototypeBean;
import com.laravelshao.learning.spring.scope.SingletonBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by shaoqinghua on 2017/12/17.
 */
public class SpringTest {

    /**
     * 1常规方式(通过自己创建对象，进行方法调用)
     */
    @Test
    public void normalWayTest() {
        UserService userService = new UserServiceImpl();
        userService.login();
    }

    /**
     * 2 IoC控制反转方式(使用工厂类获取bean对象，进行方法调用)
     */
    @Test
    public void iocWayTest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.login();
    }

    /**
     * 3 DI依赖注入方式(需配置property属性及提供对应的setter方法)
     */
    @Test
    public void diWayTest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.login();
    }

    /**
     * bean作用域测试
     */
    @Test
    public void scopeTest() {
        /**
         * 1 获取spring工厂(spring容器、spring上下文)
         */
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        /**
         * 2.从spring容器中获取bean对象
         *
         * 初始化时机
         * 单例在容器初始化时就创建对象
         * 多例在每次调用getBean时才创建对象
         */
        SingletonBean singletonBean1 = (SingletonBean) applicationContext.getBean("singletonBean");
        SingletonBean singletonBean2 = (SingletonBean) applicationContext.getBean("singletonBean");
        System.out.println(singletonBean1);
        System.out.println(singletonBean2);
        PrototypeBean prototypeBean1 = (PrototypeBean) applicationContext.getBean("prototypeBean");
        PrototypeBean prototypeBean2 = (PrototypeBean) applicationContext.getBean("prototypeBean");
        System.out.println(prototypeBean1);
        System.out.println(prototypeBean2);
    }

    /**
     * bean生命周期测试 查看初始化&销毁方法的调用
     */
    @Test
    public void lifecycleTest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        LifecycleBean lifeCycleBean = (LifecycleBean) applicationContext.getBean("lifecycleBean");
        /**
         * 没有发现销毁方法被调用
         *
         * 原因 junit结束运行时 直接干掉了jvm spring容器还没来得及销毁里面的东西
         * 解决 手动销毁spring容器
         * 注意事项
         * 单例由容器初始化时创建和容器关闭时进行销毁
         * 多例不是在容器初始化时创建 容器关闭时也不会销毁多例
         */
        ((ClassPathXmlApplicationContext) applicationContext).close();
    }

    /**
     * 属性依赖注入测试
     */
    @Test
    public void propertyDITest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        /**
         * 构造方法注入属性值
         */
        Car car = (Car) applicationContext.getBean("car");
        System.out.println(car);
        /**
         * setter方法属性注入
         */
        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);
        /**
         * p名称空间简化setter属性注入
         */
        Person person2 = (Person) applicationContext.getBean("person2");
        System.out.println(person);
        /**
         * SpEL表达式简化setter属性注入
         */
        Person person3 = (Person) applicationContext.getBean("person3");
        System.out.println(person);
    }

    /**
     * 注解方式依赖注入测试
     */
    @Test
    public void annotationDITest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        CustomerService customerService = (CustomerService) applicationContext.getBean("customerService");
        customerService.save();
    }
}
