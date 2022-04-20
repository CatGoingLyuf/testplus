package com.example.JDKProxy;


/**
 * @Author lyuf
 * @Date 2021/7/19 13:43
 * @Version 1.0
 * 需要被代理的实现类
 */
public class UserDaoImpl implements UserDao{
    @Override
    public void insert() {
        System.out.println("insert");
    }

    @Override
    public void delete() {
        System.out.println("delete");
    }
}
