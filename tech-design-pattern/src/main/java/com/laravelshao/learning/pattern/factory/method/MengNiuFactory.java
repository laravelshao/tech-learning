package com.laravelshao.learning.pattern.factory.method;

import com.laravelshao.learning.pattern.factory.MengNiu;
import com.laravelshao.learning.pattern.factory.Milk;

/**
 * Created by shaoqinghua on 2018/8/5.
 */
public class MengNiuFactory implements Factory {
    @Override
    public Milk getMilk() {
        return new MengNiu();
    }
}
