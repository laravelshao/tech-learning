package com.laravelshao.spring.framework.context;

/**
 * Created by shaoqinghua on 2018/8/18.
 */
public abstract class AbstractApplicationContext {

    /**
     * 模板方法供子类重写
     */
    protected void onRefresh() {
    }

    protected abstract void refreshBeanFactory();


}
