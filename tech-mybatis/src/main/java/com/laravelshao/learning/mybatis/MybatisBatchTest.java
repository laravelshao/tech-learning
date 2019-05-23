package com.laravelshao.learning.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author shaoqinghua
 * @date 2018/12/19
 * @description BATCH方式
 */
public class MybatisBatchTest {

    public static void main(String[] args) throws IOException {

        // 定义全局配置文件
        String resource = "mybatis-config.xml";
        // 读取配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 通过SqlSessionFactoryBuilder构建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);

        //for (int i = 0; i < 500; i++) {
        //    user = new User();
        //    user.setId("test" + i);
        //    user.setName("name" + i);
        //    user.setDelFlag("0");
        //    mapper.insert(user);
        //}

        sqlSession.commit();

        // 关闭session
        sqlSession.close();
    }
}
