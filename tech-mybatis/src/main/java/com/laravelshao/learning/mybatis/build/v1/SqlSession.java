package com.laravelshao.learning.mybatis.build.v1;

import java.lang.reflect.Proxy;

/**
 * Created by shaoqinghua on 2018/8/4.
 */
public class SqlSession {

    Configuration configuration;

    Executor executor;

    public SqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    /**
     * getMapper
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                this.getClass().getInterfaces(), new MapperProxy(this));
    }

    /**
     * selectOne
     *
     * @param statement
     * @param parameter
     * @param <T>
     * @return
     */
    public <T> T selectOne(String statement, String parameter) {
        return executor.query(statement, parameter);
    }
}
