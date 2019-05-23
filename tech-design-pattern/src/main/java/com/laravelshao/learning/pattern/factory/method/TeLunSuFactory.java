package com.laravelshao.learning.pattern.factory.method;

import com.laravelshao.learning.pattern.factory.Milk;
import com.laravelshao.learning.pattern.factory.TeLunSu;

/**
 * Created by shaoqinghua on 2018/8/5.
 */
public class TeLunSuFactory implements Factory {
    @Override
    public Milk getMilk() {
        return new TeLunSu();
    }
}
