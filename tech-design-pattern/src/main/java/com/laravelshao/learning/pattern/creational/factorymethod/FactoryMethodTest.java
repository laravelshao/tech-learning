package com.laravelshao.learning.pattern.creational.factorymethod;

import java.net.URLStreamHandlerFactory;
import java.util.Collection;

/**
 * 工厂方法模式测试
 *
 * <p>工厂方法模式案例 {@link Collection#iterator} 和 {@link URLStreamHandlerFactory#createURLStreamHandler}
 *
 * @author shaoqinghua
 * @date 2017/6/1
 * @description
 */
public class FactoryMethodTest {

    public static void main(String[] args) {
        VideoFactory videoFactory = new JavaVideoFactory();
        Video video = videoFactory.getVideo();
        video.play();
    }

}
