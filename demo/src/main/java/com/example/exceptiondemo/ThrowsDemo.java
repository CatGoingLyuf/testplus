package com.example.exceptiondemo;

import java.rmi.RemoteException;

/**
 * @Author lyuf
 * @Date 2021/7/13 14:44
 * @Version 1.0
 */
public class ThrowsDemo {
    //一个方法可以声明抛出多个异常，多个异常之间用逗号隔开。
    public void de(double num) throws RemoteException,IllegalAccessError {

        throw new RemoteException();

    }
}
