package com.laravelshao.learning.spring.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理工厂类(JDK动态代理的类必须实现接口)
 *
 * @author qinghua.shao
 * @date 2017/12/18
 * @since 1.0
 */
public class JdkProxyFactory implements InvocationHandler {

    private Object target;

    public JdkProxyFactory(Object target) {
        this.target = target;
    }

    /**
     * 获取代理对象，当前类继承InvocationHandler
     */
    public Object getProxyObject() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //添加功能
        System.out.println("增强代码，添加日志功能");
        //执行原有方法
        return method.invoke(target, args);
    }
}
