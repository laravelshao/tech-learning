package com.laravelshao.spring.my.annotation;

import java.lang.annotation.*;


@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestParam {
    String value() default "";
}
