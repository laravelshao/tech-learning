package com.laravelshao.learning.mybatis.build.v1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by shaoqinghua on 2018/8/4.
 */
public class MapperProxy implements InvocationHandler {

    private SqlSession sqlSession;

    public MapperProxy(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
