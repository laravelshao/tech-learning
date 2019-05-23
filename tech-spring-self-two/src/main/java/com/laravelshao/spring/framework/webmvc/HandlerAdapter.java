package com.laravelshao.spring.framework.webmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by shaoqinghua on 2018/8/18.
 */
public class HandlerAdapter {

    private Map<String, Integer> paramMapping;

    public HandlerAdapter(Map<String, Integer> paramMapping) {
        this.paramMapping = paramMapping;
    }

    /**
     * 处理请求返回视图对象
     *
     * @param req
     * @param resp
     * @param handler 传递handler原因：handler中包含controller、method、url信息
     * @return
     */
    public ModelAndView handle(HttpServletRequest req, HttpServletResponse resp, HandlerMapping handler) throws
            InvocationTargetException, IllegalAccessException {
        /**
         * 根据用户请求的参数信息，跟method中的参数信息进行动态匹配
         * resp传进来的目的只有一个：只是为了将其赋值给方法参数，仅此而已
         */


        // 1 准备方法形参列表
        Class<?>[] paramTypes = handler.getMethod().getParameterTypes();

        // 2 获取自定义命名参数所在位置 用户通过URL传过来的参数列表
        Map<String, String[]> reqParameterMap = req.getParameterMap();

        // 3 构造实参列表
        Object[] paramValues = new Object[paramTypes.length];
        for (Map.Entry<String, String[]> param : reqParameterMap.entrySet()) {
            String value = Arrays.toString(param.getValue()).replaceAll("\\[|\\]", "").replaceAll("\\s", "");
            if (!this.paramMapping.containsKey(param.getKey())) {
                continue;
            }

            Integer index = this.paramMapping.get(param.getKey());
            paramValues[index] = caseStringValue(value, paramTypes[index]);
        }

        if (this.paramMapping.containsKey(HttpServletRequest.class.getName())) {
            Integer reqIndex = this.paramMapping.get(HttpServletRequest.class.getName());
            paramValues[reqIndex] = req;
        }

        if (this.paramMapping.containsKey(HttpServletResponse.class.getName())) {
            Integer respIndex = this.paramMapping.get(HttpServletResponse.class.getName());
            paramValues[respIndex] = resp;
        }

        // 4 从handler中取出controller、method 利用反射机制调用
        Object res = handler.getMethod().invoke(handler.getController(), paramValues);
        if (res == null) {
            return null;
        }

        boolean isMV = handler.getMethod().getReturnType() == ModelAndView.class;
        if (isMV) {
            return (ModelAndView) res;
        } else {
            return null;
        }
    }

    private Object caseStringValue(String value, Class<?> clazz) {
        if (clazz == String.class) {
            return value;
        } else if (clazz == Integer.class) {
            return Integer.valueOf(value);
        } else if (clazz == int.class) {
            return Integer.valueOf(value).intValue();
        } else {
            return null;
        }
    }
}
