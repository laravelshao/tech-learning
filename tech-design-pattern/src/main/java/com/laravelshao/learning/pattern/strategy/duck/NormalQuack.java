package com.laravelshao.learning.pattern.strategy.duck;

/**
 * 正常嘎嘎叫实现
 * Created by shaoqinghua on 2018/7/14.
 */
public class NormalQuack implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("正常嘎嘎叫");
    }
}
