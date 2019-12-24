package com.laravelshao.learning.postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author qinghua.shao
 * @date 2019/12/24
 * @since 1.0.0
 */
public class PostgreSQLTest {

    public static void main(String[] args) throws SQLException {

        String url = "jdbc:postgresql://localhost:5432/sylvia";
        Properties props = new Properties();
        props.setProperty("user", "sylvia");
        props.setProperty("password", "sylvia");
        Connection conn = DriverManager.getConnection(url, props);

        //String url = "jdbc:postgresql://localhost/test?user=fred&password=secret&ssl=true";
        //Connection conn = DriverManager.getConnection(url);

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM demo.weather WHERE city = 'San Francisco'");
        while (rs.next())
        {
            System.out.print("Column 1 returned ");
            System.out.println(rs.getString(1));
        }
        rs.close();
        st.close();
    }
}
