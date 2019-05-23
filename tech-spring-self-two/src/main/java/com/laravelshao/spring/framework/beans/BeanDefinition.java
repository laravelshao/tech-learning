package com.laravelshao.spring.framework.beans;

import lombok.Data;

/**
 * Created by shaoqinghua on 2018/8/15.
 */
@Data
public class BeanDefinition {

    private String beanClassName;
    private String factoryBeanName;
    private boolean lazyInit = false;

    public boolean isLazyInit() {
        return lazyInit;
    }

}
