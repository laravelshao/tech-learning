package com.laravelshao.learning.pattern.creational.factorymethod;

/**
 * Python视频工厂实现
 *
 * @author shaoqinghua
 * @date 2017/6/1
 * @description
 */
public class PythonVideoFactory extends VideoFactory {

    @Override
    public Video getVideo() {
        return new PythonVideo();
    }
}
