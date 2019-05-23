package com.laravelshao.learning.spring.instance;

/**
 * Created by shaoqinghua on 2017/12/17.
 */
public class Bean2Factory {

    //提供静态方法 返回对象的实例
    public static Bean2 getBean2() {
        return new Bean2();
    }
}
