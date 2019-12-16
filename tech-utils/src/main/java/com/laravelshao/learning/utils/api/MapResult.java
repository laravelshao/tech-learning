package com.laravelshao.learning.utils.api;

import java.util.Map;

/**
 * MAP结果封装对象
 *
 * @author qinghua.shao
 * @date 2019/12/13
 * @since 1.0.0
 */
public class MapResult<K, V> extends Result<Map<K, V>> {

    protected MapResult() {
    }

    protected MapResult(Map<K, V> data) {
        this.data = data;
    }
}
