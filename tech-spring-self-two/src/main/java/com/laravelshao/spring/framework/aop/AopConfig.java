package com.laravelshao.spring.framework.aop;

import lombok.Data;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shaoqinghua on 2018/8/18.
 */
public class AopConfig {

    /**
     * 以目标对象需要增强的Method作为key，需要增强的代码内容作为value
     */
    private Map<Method, Aspect> points = new HashMap<>();

    public void put(Method target, Object aspect, Method[] points) {
        this.points.put(target, new Aspect(aspect, points));
    }

    public Aspect get(Method method) {
        return this.points.get(method);
    }

    public boolean contains(Method method) {
        return this.points.containsKey(method);
    }

    @Data
    public class Aspect {
        private Object aspect; // LogAspect对象会赋值给aspect
        private Method[] points; // LogAspect的before和after方法会赋值给points

        public Aspect(Object aspect, Method[] points) {
            this.aspect = aspect;
            this.points = points;
        }
    }

}
