package com.aop;

/**
 * @Author lyuf
 * @Date 2021/7/19 15:41
 * @Version 1.0
 */

//使用cjlib
public class CGLibServiceImpl {
    public void barB(String msg, int type) {
        System.out.println("BServiceImpl.barB(msg:" + msg + " type:" + type + ")");
        if (type == 1)
            throw new IllegalArgumentException("测试异常");
    }

    public void fooB() {
        System.out.println("BServiceImpl.fooB()");
    }
}