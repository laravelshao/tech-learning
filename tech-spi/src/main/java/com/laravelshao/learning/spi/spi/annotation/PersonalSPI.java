package com.laravelshao.learning.spi.spi.annotation;

import java.lang.annotation.*;

/**
 * Created by shaoqinghua on 2018/7/17.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface PersonalSPI {

    /**
     * 缺省扩展点名
     */
    String value() default "";
}
