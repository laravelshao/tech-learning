package com.laravelshao.spring.framework.core;

/**
 * Created by shaoqinghua on 2018/8/11.
 */
public interface BeanFactory {

    /**
     * 根据bean名称获取实例
     *
     * @param beanName
     * @return
     */
    Object getBean(String beanName);

}
