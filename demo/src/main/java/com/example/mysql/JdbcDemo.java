package com.example.mysql;

import org.junit.Test;
import java.sql.*;


/**
 * @Author lyuf
 * @Date 2021/7/15 11:23
 * @Version 1.0
 */
public class JdbcDemo {
    private static String URL = "jdbc:mysql://localhost:3306/test??useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8&useSSL=false"; //数据库链接的url
    private static String USER = "root";   //数据库的用户名
    private static String PASSWORD = "root";   //数据库的密码

    @Test
    public void test1() throws ClassNotFoundException, SQLException {
        //实例化对象
        Connection connection = null;
        Statement statement = null;

        //注册数据库的驱动
        Class.forName("com.mysql.jdbc.Driver");

        //通过驱动管理设置链接的必要信息
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        statement =  connection.createStatement();

        //创建执行sql的对象
        String sql = "select * from login where username = 'root'";
        String sql2 = "UPDATE login SET `password` = 'root' WHERE username = 'root' ";
        //影响行数
        int count = statement.executeUpdate(sql2);
        System.out.println("你的操作影响了"+count+"行");
        ResultSet resultSet = statement.executeQuery(sql);

        //迭代的两种方法
        if (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("username");
            String password = resultSet.getString("password");
            String roles = resultSet.getString("roles");
            System.out.println(id + "\t" + name + "\t" + password + "\t" + roles);
        }


       if (resultSet.next()){
           int id = resultSet.getInt(1);
           String name = resultSet.getString(2);
           String password = resultSet.getString(3);
           String roles = resultSet.getString(4);
           System.out.println(id + "\t" + name + "\t" + password + "\t" + roles);
       }

        //释放资源
        if (statement != null) {
            statement.close();
        }

        //释放链接
        if (connection != null) {
            connection.close();
        }



    }

}
