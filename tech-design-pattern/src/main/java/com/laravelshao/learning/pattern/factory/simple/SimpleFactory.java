package com.laravelshao.learning.pattern.factory.simple;

import com.laravelshao.learning.pattern.factory.MengNiu;
import com.laravelshao.learning.pattern.factory.Milk;
import com.laravelshao.learning.pattern.factory.TeLunSu;
import com.laravelshao.learning.pattern.factory.YiLi;

/**
 * Created by shaoqinghua on 2018/8/5.
 */
public class SimpleFactory {

    public Milk getMilk(String name) {
        if ("蒙牛".equals(name)) {
            return new MengNiu();
        } else if ("伊利".equals(name)) {
            return new YiLi();
        } else if ("特仑苏".equals(name)) {
            return new TeLunSu();
        } else {
            System.out.println("不能生产指定的牛奶");
            return null;
        }
    }

}
