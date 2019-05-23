package com.laravelshao.spring.framework.beans;

import com.laravelshao.spring.framework.aop.AopConfig;
import com.laravelshao.spring.framework.aop.AopProxy;

/**
 * Created by shaoqinghua on 2018/8/15.
 */
public class BeanWrapper {

    // 包装对象
    private Object wrappedInstance;
    // 原始对象
    private Object originalInstance;

    private AopProxy aopProxy = new AopProxy();

    public BeanWrapper(Object instance) {
        this.wrappedInstance = aopProxy.getProxy(instance);
        this.originalInstance = instance;
    }

    /**
     * 支持事件响应(观察者模式)
     */
    private BeanPostProcessor postProcessor;

    public BeanPostProcessor getPostProcessor() {
        return postProcessor;
    }

    public void setPostProcessor(BeanPostProcessor postProcessor) {
        this.postProcessor = postProcessor;
    }

    public Object getWrappedInstance() {
        return this.wrappedInstance;
    }

    public Object getOriginalInstance() {
        return this.originalInstance;
    }

    public void setAopConfig(AopConfig config) {
        aopProxy.setAopConfig(config);
    }

}
