package com.laravelshao.learning.pattern.creational.abstractfactory;

/**
 * 课程工厂
 *
 * @author shaoqinghua
 * @date 2017/6/1
 * @description
 */
public interface CourseFactory {

    /**
     * 获取视频
     *
     * @return
     */
    Video getVideo();

    /**
     * 获取笔记
     *
     * @return
     */
    Note getNote();
}
