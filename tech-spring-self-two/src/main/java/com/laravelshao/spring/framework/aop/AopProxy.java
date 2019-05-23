package com.laravelshao.spring.framework.aop;

import lombok.Data;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by shaoqinghua on 2018/8/18.
 */
@Data
public class AopProxy implements InvocationHandler {

    private Object target;

    private AopConfig aopConfig;


    /**
     * 获取代理对象
     *
     * @param instance 原始对象
     * @return
     */
    public Object getProxy(Object instance) {
        this.target = instance;
        Class<?> clazz = instance.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Method m = this.target.getClass().getMethod(method.getName(), method.getParameterTypes());

        // 原始方法执行前调用增强方法
        // 通过原生方法查找 代理方法无法找到
        if (aopConfig.contains(m)) {
            AopConfig.Aspect aspect = aopConfig.get(m);
            aspect.getPoints()[0].invoke(aspect.getAspect());
        }

        // 反射调用原始方法
        Object obj = method.invoke(this.target, args);
        System.out.println(args);

        // 原始方法执行后调用增强方法
        if (aopConfig.contains(m)) {
            AopConfig.Aspect aspect = aopConfig.get(m);
            aspect.getPoints()[1].invoke(aspect.getAspect());
        }

        return obj;
    }
}
