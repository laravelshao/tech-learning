package com.laravelshao.learning.mybatis;

import com.laravelshao.learning.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Mybatis测试
 * Created by shaoqinghua on 2018/7/21.
 */
public class MybatisTest {

    public static void main(String[] args) throws IOException {

        // 定义全局配置文件
        String resource = "mybatis-config.xml";
        // 读取配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 通过SqlSessionFactoryBuilder构建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // statement引用:命名空间.statementId
        User user = sqlSession.selectOne("abc.queryUserById", 1L);
        System.out.println(user);

        // 关闭session
        sqlSession.close();
    }
}
