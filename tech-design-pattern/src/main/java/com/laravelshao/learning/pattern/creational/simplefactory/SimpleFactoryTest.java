package com.laravelshao.learning.pattern.creational.simplefactory;

/**
 * 简单工厂模式测试
 *
 *
 * @author shaoqinghua
 * @date 2017/6/1
 * @description
 */
public class SimpleFactoryTest {

    public static void main(String[] args) {
        SimpleFactory factory = new SimpleFactory();
        factory.getVideo("java").play();
        factory.getVideo(JavaVideo.class).play();
    }

}
