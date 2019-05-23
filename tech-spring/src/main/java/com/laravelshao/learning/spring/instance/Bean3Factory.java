package com.laravelshao.learning.spring.instance;

/**
 * Created by shaoqinghua on 2017/12/17.
 */
public class Bean3Factory {

    //提供一个实例方法返回Bean3对象
    public Bean3 getBean3() {
        return new Bean3();
    }
}
