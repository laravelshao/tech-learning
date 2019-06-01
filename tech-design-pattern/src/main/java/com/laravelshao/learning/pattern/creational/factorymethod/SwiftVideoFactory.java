package com.laravelshao.learning.pattern.creational.factorymethod;

/**
 * @author shaoqinghua
 * @date 2017/6/1
 * @description
 */
public class SwiftVideoFactory extends VideoFactory {
    @Override
    public Video getVideo() {
        return new SwiftVideo();
    }
}
