package com.laravelshao.learning.spring.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by shaoqinghua on 2017/12/18.
 * CGLIB动态代理工厂类
 */
public class CglibProxyFactory implements MethodInterceptor {

    private Object target;

    public CglibProxyFactory(Object target) {
        this.target = target;
    }

    /**
     * 获取代理对象
     *
     * @return
     */
    public Object getProxyObject() {
        Enhancer enhancer = new Enhancer();
        //设置两个参数
        //设置生成代理类的目标对象（代理类对象是目标对象的子类）
        enhancer.setSuperclass(target.getClass());
        //设置回调方法
        enhancer.setCallback(this);
        //生成代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //添加功能
        System.out.println("增强代码，添加日志功能");
        //执行原有方法
        return method.invoke(target, objects);
    }
}
