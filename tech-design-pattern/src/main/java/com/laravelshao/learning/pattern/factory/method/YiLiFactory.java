package com.laravelshao.learning.pattern.factory.method;

import com.laravelshao.learning.pattern.factory.Milk;
import com.laravelshao.learning.pattern.factory.YiLi;

/**
 * Created by shaoqinghua on 2018/8/5.
 */
public class YiLiFactory implements Factory {
    @Override
    public Milk getMilk() {
        return new YiLi();
    }
}
