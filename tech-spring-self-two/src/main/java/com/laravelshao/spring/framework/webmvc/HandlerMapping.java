package com.laravelshao.spring.framework.webmvc;

import lombok.Data;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * Created by shaoqinghua on 2018/8/18.
 */
@Data
public class HandlerMapping {

    private Pattern pattern; // url的封装
    private Object controller;
    private Method method;

    public HandlerMapping(Pattern pattern, Object controller, Method method) {
        this.pattern = pattern;
        this.controller = controller;
        this.method = method;
    }

}
