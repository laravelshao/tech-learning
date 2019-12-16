package com.laravelshao.learning.utils.api;

import java.util.List;

/**
 * LIST结果封装对象
 *
 * @author qinghua.shao
 * @date 2019/12/13
 * @since 1.0.0
 */
public class ListResult<T> extends Result<List<T>> {

    protected ListResult() {
    }

    public ListResult(List<T> data) {
        this.data = data;
    }

}
