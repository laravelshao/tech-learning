package com.laravelshao.learning.pattern.factory.abstr;

import com.laravelshao.learning.pattern.factory.*;
import com.laravelshao.learning.pattern.factory.method.MengNiuFactory;
import com.laravelshao.learning.pattern.factory.method.TeLunSuFactory;
import com.laravelshao.learning.pattern.factory.method.YiLiFactory;

/**
 * Created by shaoqinghua on 2018/8/5.
 */
public class MilkFactory extends AbstractFactory {
    @Override
    public Milk getMengNiu() {
        return new MengNiu();
        //return new MengNiuFactory().getMilk(); //抽象工厂与工厂方法混合使用
    }

    @Override
    public Milk getYiLi() {
        return new YiLi();
        //return new YiLiFactory().getMilk();
    }

    @Override
    public Milk getTeLunSu() {
        return new TeLunSu();
        //return new TeLunSuFactory().getMilk();
    }

    @Override
    public Milk getSanLu() {
        return new SanLu();
    }
}
