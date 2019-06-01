package com.laravelshao.learning.pattern.creational.simplefactory;

import org.slf4j.LoggerFactory;

import java.util.Calendar;

/**
 * 简单工厂模式(不属于23种设计模式)
 *
 * <p>使用简单工厂模式的案例 {@link Calendar#getInstance} 和 {@link LoggerFactory#getLogger}
 *
 * @author shaoqinghua
 * @date 2017/6/1
 * @description
 */
public class SimpleFactory {

    /**
     * 方式1：传入名称获取，不利于后期扩展，如果不需要扩展可声明为静态方法
     *
     * @param name
     * @return
     */
    public Video getVideo(String name) {
        if ("java".equals(name)) {
            return new JavaVideo();
        } else if ("python".equals(name)) {
            return new PythonVideo();
        }
        return null;
    }

    /**
     * 方式2：反射方式获取，传入类型方便后期扩展
     *
     * @param clazz
     * @return
     */
    public Video getVideo(Class clazz) {
        Video video = null;
        try {
            video = (Video) Class.forName(clazz.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return video;
    }
}
