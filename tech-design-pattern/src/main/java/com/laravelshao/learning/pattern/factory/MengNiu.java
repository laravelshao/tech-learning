package com.laravelshao.learning.pattern.factory;

/**
 * Created by shaoqinghua on 2018/8/5.
 */
public class MengNiu implements Milk {
    
    @Override
    public String getMilk() {
        return "蒙牛";
    }
}
