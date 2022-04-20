package com.example.exceptiondemo;

/**
 * @Author lyuf
 * @Date 2021/7/13 14:50
 * @Version 1.0
 */
public class MyExceptionDemo extends Exception{

    public MyExceptionDemo(String message) {
        super(message);
        System.out.println("自定义异常");
    }

}

