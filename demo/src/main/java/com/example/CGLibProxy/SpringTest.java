package com.example.CGLibProxy;

import org.apache.catalina.User;
import org.junit.Test;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

/**
 * @Author lyuf
 * @Date 2021/7/19 14:36
 * @Version 1.0
 */
public class SpringTest {

    private UserDaoImpl userDao = new UserDaoImpl();

    @Test
    public void test() {
        userDao.delete();
        userDao.insert();
    }

    @Test
    public void test2() {

//        CGLibProxy cgLibProxy = new CGLibProxy(userDao);
//        // 1.创建核心类:
//        Enhancer enhancer = new Enhancer();
//        //2.为其设置父类
//        enhancer.setSuperclass(userDao.getClass());
//        //3.设置回调
//        enhancer.setCallback(cgLibProxy);
//        //4.创建代理
//        UserDao userDaoProxy = (UserDao) enhancer.create();

        UserDao proxy = new CGLibProxy(userDao).createProxy();

        proxy.insert();
        proxy.delete();

    }
}
