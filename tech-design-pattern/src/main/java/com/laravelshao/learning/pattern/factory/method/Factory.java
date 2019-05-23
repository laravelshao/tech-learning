package com.laravelshao.learning.pattern.factory.method;

import com.laravelshao.learning.pattern.factory.Milk;

/**
 * Created by shaoqinghua on 2018/8/5.
 */
public interface Factory {

    /**
     * 统一的工厂方法
     *
     * @return
     */
    Milk getMilk();
}
