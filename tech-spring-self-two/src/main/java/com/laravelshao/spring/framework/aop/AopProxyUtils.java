package com.laravelshao.spring.framework.aop;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

/**
 * Created by shaoqinghua on 2018/8/18.
 */
public class AopProxyUtils {


    public static Object getTargetObject(Object proxy) throws Exception {

        if (!isAopProxy(proxy)) {
            return proxy;
        }

        return getProxyTargetObject(proxy);
    }

    /**
     * 判断是否是代理对象
     *
     * @param object
     * @return
     */
    public static boolean isAopProxy(Object object) {
        return Proxy.isProxyClass(object.getClass());
    }

    /**
     * 获取代理类真实目标对象
     *
     * @return
     */
    public static Object getProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
        h.setAccessible(true);
        AopProxy aopProxy = (AopProxy) h.get(proxy);
        Field target = aopProxy.getClass().getDeclaredField("target");
        target.setAccessible(true);
        return target.get(aopProxy);

    }
}
