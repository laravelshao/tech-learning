package com.laravelshao.spring.framework.annotation;

import java.lang.annotation.*;

/**
 * 请求隐射
 * @author Tom
 *
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {

	String value() default "";
}
