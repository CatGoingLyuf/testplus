package com.lyuf.uitl;

import org.junit.Test;

import java.sql.*;
import java.util.Scanner;

/**
 * @Author lyuf
 * @Date 2021/7/15 16:17
 * @Version 1.0
 */
//封装jdbc
public class JdbcUtil {
    //连接数据库所需资源
    private static String diverClass = "com.mysql.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/test??useUnicode=true&allowPublicKeyRetrieval=true&characterEncoding=utf-8&serverTimezone=GMT%2B8&useSSL=false"; //数据库链接的url
    private static String USER = "root";   //数据库的用户名
    private static String PASSWORD = "root";   //数据库的密码

    // 利用static修饰的静态代码块，在程序的加载阶段处理驱动问题
    static {
        try {
            Class.forName(diverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //获取数据库链接
    public static Connection getConnection() throws SQLException {
        //通过驱动管理器获取链接
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }

    //链接断开
    public static void close(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void close(Connection connection) throws SQLException {
        close(connection, null, null);
    }

    public static void close(Connection connection, Statement statement) throws SQLException {
        close(connection, statement, null);
    }
}
