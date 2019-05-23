package com.laravelshao.learning.mybatis.build.v1;

/**
 * Created by shaoqinghua on 2018/8/4.
 */
public interface Executor {

    <T> T query(String statement, String parameter);
}
