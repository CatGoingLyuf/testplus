package com.example.mysql;

import org.junit.Test;

import java.sql.*;
import java.util.Scanner;

/**
 * @Author lyuf
 * @Date 2021/7/15 14:12
 * @Version 1.0
 */
public class PrepareDemo {
    private static String URL = "jdbc:mysql://localhost:3306/test??useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8&useSSL=false"; //数据库链接的url
    private static String USER = "root";   //数据库的用户名
    private static String PASSWORD = "root";   //数据库的密码

    @Test
    public void test1() throws ClassNotFoundException, SQLException {
        //实例化控制台输入
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入用户名");
        String username = scanner.nextLine();
        System.out.println("输入密码");
        String pwd = scanner.nextLine();

        //注册数据库的驱动
        Class.forName("com.mysql.jdbc.Driver");

        //通过驱动管理设置链接的必要信息
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

        //创建执行sql的对象
        String sql = "select * from login where username = ? and password = ?";

        //进行预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, username);
        preparedStatement.setObject(2, pwd);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            System.out.println("登录成功！");
        } else {
            System.out.println("用户名或密码错误！");
        }

        //释放资源
        resultSet.close();

        preparedStatement.close();


        //释放链接
        if (connection != null) {
            connection.close();
        }


    }
}
