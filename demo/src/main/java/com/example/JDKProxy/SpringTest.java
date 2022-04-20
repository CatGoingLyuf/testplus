package com.example.JDKProxy;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @Author lyuf
 * @Date 2021/7/19 13:44
 * @Version 1.0
 */
public class SpringTest {


    private UserDaoImpl userDao = new UserDaoImpl();

    @Test
    public void test() {
        userDao.insert();
        userDao.delete();
    }

    @Test
    public void test2() {

        JDKProxy jdkProxy = new JDKProxy(userDao);

        UserDao proxy = (UserDao) Proxy.newProxyInstance(userDao.getClass().getClassLoader(),
                userDao.getClass().getInterfaces(),jdkProxy);

        proxy.insert();
        proxy.delete();
    }

}
