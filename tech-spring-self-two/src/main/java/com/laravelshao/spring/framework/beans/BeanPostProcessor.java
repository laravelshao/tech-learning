package com.laravelshao.spring.framework.beans;

/**
 * 用于事件监听
 * Created by shaoqinghua on 2018/8/15.
 */
public class BeanPostProcessor {

    /**
     * 在Bean初始化前提供回调入口
     *
     * @param bean
     * @param beanName
     * @return
     */
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    /**
     * 在Bean初始化后提供回调入口
     *
     * @param bean
     * @param beanName
     * @return
     */
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}
