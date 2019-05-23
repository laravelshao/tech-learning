package com.laravelshao.learning.spring.instance;

import org.springframework.beans.factory.FactoryBean;

/**
 * Created by shaoqinghua on 2017/12/17.
 */
public class MyBean4FactoryBean implements FactoryBean<Bean4> {

    public Bean4 getObject() throws Exception {
        return new Bean4();
    }

    public Class<?> getObjectType() {
        return null;
    }
}
