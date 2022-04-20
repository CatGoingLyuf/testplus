package com.aop;

/**
 * @Author lyuf
 * @Date 2021/7/19 15:39
 * @Version 1.0
 */

//使用jdk动态代理
public class JDKServiceImpl {

    public void barA() {
        System.out.println("JDKServiceImpl.barA");
    }

    public void fooA(String msg) {
        System.out.println("JDKServiceImpl.fooA : " + msg + "");
    }
}
