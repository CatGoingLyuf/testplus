package com.lyuf;

import com.lyuf.uitl.JdbcUtil;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO层基类
 *      用于处理SQL语句
 *      1. 更新方法 update
 *      2. 查询方法 query
 *
 * BaseDao使用：
 *      业务所需处理的 Dao层，继承 BaseDao，然后通过super关键字调用update或者query方法
 *      例如:
 *          StudentDao WorkerDao ==> BaseDao子类
 */
public class BaseDao {
    /*
    update方法分析
        处理 insert update delete...
            Query OK, 1 row affected
        权限修饰符:
            public
        是否静态:
            不需要
        返回值类型：
            int 类型，返回当前SQL语句操作对于数据库的影响行数。
        方法名:
            update
        形式参数列表:
            1. String SQL 需要操作的SQL语句
            2. 对应SQL语句的参数！！！
                   a. 参数类型多种多样！！！
                   b. 参数个数不确定！！！
                   Object... parameters
     方法声明:
        public int update(String sql, Object... parameters);
            补充说明：
                1. 不定长参数需要在方法参数列表的最后
                2. 不定长参数在方法内有且只允许一个
     */

    /**
     * 通用Update方法，可以处理insert，update，delete SQL语句
     *
     * @param sql 需要进行处理执行的SQL语句
     * @param parameters 对应当前SQL语句参数列表，为不定长参数
     * @return 当前SQL语句执行数据库受到影响的行数
     */
    public int update(String sql, Object... parameters) throws SQLException {
        // 1. 获取数据库连接
        int affectedRows = 0;
        Connection connection = JdbcUtil.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            // 2. 预处理SQL语句
            preparedStatement = getPreparedStatement(sql, connection, parameters);

            // 4. 执行 SQL语句
            affectedRows = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection, preparedStatement);
        }

        return affectedRows;
    }


    /**
     * 通用Query方法，可以处理DQL语句，根据用户指定的数据类型，返回值是List集合中带有
     * 用户指定的数据类型
     *
     * @param sql        需要处理的DQL语句
     * @param cls        用户指定的类型，需要的参数为Class类型
     * @param parameters 对应当前SQL语句的参数，为不定长参数方式
     * @param <T>        当前方法声明的自定义泛型占位符。
     * @return 带有用户指定数据类型的List集合，如果没有任何一个数据，返回null
     */
    public <T> List<T> query(String sql, Class<T> cls, Object... parameters) throws SQLException {
        List<T> list = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        connection = JdbcUtil.getConnection();

        try {
            // SQL语句预处理和参数赋值完毕
            preparedStatement = getPreparedStatement(sql, connection, parameters);

            // 数据库查询结果集
            resultSet = preparedStatement.executeQuery();

            // 获取结果集源数据
            ResultSetMetaData metaData = resultSet.getMetaData();
            // 查询结果集字段个数
            int columnCount = metaData.getColumnCount();

            list = new ArrayList<>();
            while (resultSet.next()) {
                /*
                1. for循环遍历整个字段个数
                2. 拿到对应的字段名字
                3. 获取对应字段数据
                4. 给予类对象对应成员变量赋值操作。
                    可以通过当前类对象对应成员变量的数据类型，对于数据库中
                    查询内容进行赋值操作，并且带有数据类型转换和检查能力。

                    数据库 Object类型数据 ==> 对应成员变量数据类型数据。
                    BeanUtils 第三方工具！！！
                 */
                T obj = cls.getConstructor().newInstance();

                for (int i = 1; i <= columnCount; i++) {
                    // 对应字段下标的字段名
                    String columnName = metaData.getColumnName(i);
                    // 对应字段的数据库数据， 字段 ==> Java类对象成员变量名字
                    Object value = resultSet.getObject(columnName);

                    // 利用BeanUtils工具赋值对应类对象，成员变量数据
                    BeanUtils.setProperty(obj, columnName, value);
                }

                // 存入到List集合中
                list.add(obj);
            }

        } catch (SQLException | InstantiationException | InvocationTargetException
                | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection,preparedStatement, resultSet);
        }

        // 判断list不为null，并且list中存在对应的数据，返回list集合对象，如果没有任何数据返回null
        return list != null && list.size() != 0 ? list : null;
    }

    /**
     * 类内私有化工具方法，用于提供SQL语句预处理PreparedStatement对象，获取和参数赋值方法
     *
     * @param sql        SQL语句
     * @param connection 数据库连接对象
     * @param parameters SQL语句对应的参数
     * @return 处理结果的PreparedStatement对象
     * @throws SQLException 当前方法运行出现的SQL异常
     */
    private PreparedStatement getPreparedStatement(String sql, Connection connection, Object[] parameters) throws SQLException {
        PreparedStatement preparedStatement;// 2. 预处理SQL语句
        preparedStatement = connection.prepareStatement(sql);
        /*
        3. 给予当时SQL语句参数赋值操作
            a. 没有参数
            b. SQL语句参数个数和给定的参数数组不一致！！
        */
        int parameterCount = preparedStatement.getParameterMetaData().getParameterCount();

        // 根据SQL语句参数个数和参数数组，赋值SQL语句内容
        if (!(parameters.length == 0 || parameterCount != parameters.length)) {
            for (int i = 1; i <= parameterCount; i++) {
                preparedStatement.setObject(i, parameters[i - 1]);
            }
        }
        return preparedStatement;
    }
}
