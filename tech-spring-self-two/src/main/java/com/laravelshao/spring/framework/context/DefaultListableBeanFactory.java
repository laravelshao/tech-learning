package com.laravelshao.spring.framework.context;

import com.laravelshao.spring.framework.beans.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by shaoqinghua on 2018/8/18.
 */
public class DefaultListableBeanFactory extends AbstractApplicationContext {

    /**
     * BeanDefinition存储集合
     */
    protected Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    protected void onRefresh() {
    }
    @Override
    protected void refreshBeanFactory() {

    }
}