package com.laravelshao.learning.zookeeper.rmi.rpc.server.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author shaoqinghua
 * @date 2018/10/21
 * @description
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RpcAnnotation {

    /**
     * 对外发布服务的接口地址
     *
     * @return
     */
    Class<?> value();

    /**
     * 版本
     *
     * @return
     */
    String version() default "";
}
