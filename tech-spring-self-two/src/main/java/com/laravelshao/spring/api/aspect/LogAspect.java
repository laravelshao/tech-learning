package com.laravelshao.spring.api.aspect;

/**
 * Created by shaoqinghua on 2018/8/18.
 */
public class LogAspect {

    // 调用方法前执行before方法
    public void before() {
        System.out.println("before->执行方法前记录日志......");
    }

    // 调用方法后执行after方法
    public void after() {
        System.out.println("after->执行方法后记录日志......");
    }
}
