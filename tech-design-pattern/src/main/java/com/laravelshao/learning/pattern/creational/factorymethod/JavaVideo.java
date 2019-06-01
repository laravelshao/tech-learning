package com.laravelshao.learning.pattern.creational.factorymethod;

/**
 * @author shaoqinghua
 * @date 2017/6/1
 * @description
 */
public class JavaVideo extends Video {

    @Override
    public void play() {
        System.out.println("播放Java视频");
    }
}
