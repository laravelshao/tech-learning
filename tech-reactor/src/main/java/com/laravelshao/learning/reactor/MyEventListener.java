package com.laravelshao.learning.reactor;

import java.util.List;

/**
 * 自定义事件监听器
 *
 * @author qinghua.shao
 * @date 2020/1/2
 * @since 1.0.0
 */
interface MyEventListener<T> {

    void onDataChunk(List<T> chunk);

    void processComplete();
}