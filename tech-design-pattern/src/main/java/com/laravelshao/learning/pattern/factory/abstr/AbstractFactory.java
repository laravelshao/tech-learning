package com.laravelshao.learning.pattern.factory.abstr;

import com.laravelshao.learning.pattern.factory.Milk;

/**
 * 抽象工厂是用户主入口
 * Created by shaoqinghua on 2018/8/5.
 */
public abstract class AbstractFactory {

    /**
     * 获取蒙牛牛奶
     *
     * @return
     */
    public abstract Milk getMengNiu();

    /**
     * 获取伊利牛奶
     *
     * @return
     */
    public abstract Milk getYiLi();

    /**
     * 获取特仑苏牛奶
     *
     * @return
     */
    public abstract Milk getTeLunSu();

    /**
     * 获取新增三鹿牛奶
     * @return
     */
    public abstract Milk getSanLu();

}
