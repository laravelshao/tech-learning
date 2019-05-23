package com.laravelshao.learning.spring.lifecycle;

/**
 * Created by shaoqinghua on 2017/12/17.
 */
public class LifecycleBean {

    public LifecycleBean() {
        System.out.println("LifecycleBean......构造方法被调用");
    }

    public void init() {
        System.out.println("LifecycleBean......init()方法初始化时被调用");
    }

    public void destroy() {
        System.out.println("LifecycleBean......destroy()方法销毁时被调用");
    }

}
