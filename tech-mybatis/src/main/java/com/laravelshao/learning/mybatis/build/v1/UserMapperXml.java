package com.laravelshao.learning.mybatis.build.v1;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shaoqinghua on 2018/8/6.
 */
public class UserMapperXml {

    public static final String namespace = "com.laravelshao.learning.mybatis.mapper.UserMapper";

    public static final Map<String, String> methodSqlMappings = new HashMap<>();

    static {
        methodSqlMappings.put("queryUserById", "SELECT * FROM tb_user WHERE id = %d");
    }
}
