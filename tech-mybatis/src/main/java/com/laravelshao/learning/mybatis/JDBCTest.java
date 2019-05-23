package com.laravelshao.learning.mybatis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * JDBC连接数据库测试
 * Created by shaoqinghua on 2018/7/21.
 */
public class JDBCTest {

    public static void main(String[] args) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // 加载驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 创建数据库连接
            String url = "jdbc:mysql://127.0.0.1:3306/mybatis";
            String user = "root";
            String password = "123456";
            connection = DriverManager.getConnection(url, user, password);

            // 获取statement对象
            String sql = "SELECT * FROM tb_user WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);

            // 设置参数  2个参数 第一个为下标从1开始 第二个参数值
            preparedStatement.setLong(1, 1L);

            // 执行查询
            resultSet = preparedStatement.executeQuery();

            // 遍历结果集
            while (resultSet.next()) {
                System.out.println("id = " + resultSet.getLong("id"));
                System.out.println("name = " + resultSet.getString("name"));
                System.out.println("passwd = " + resultSet.getString("password"));
            }
        } finally {
            // 关闭连接释放资源
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
