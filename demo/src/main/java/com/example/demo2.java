package com.example;
/**
 * 文档注释
 * time:2021.7.8
 * author:lyuf
 */

public class demo2 {
    //类常量
    public static final int b = 1;

    public static void main(String[] args) {
        //注释 变量 常量 运算符
        //单行注释
          /*
        多行注释
         */

        int a = 1;
        int myAge = 18;

        final int a2 = 1;
        System.out.println(myAge);
        System.out.println(b);

        //运算符
        a += b;
        System.err.println(a);
        a %= b;
        System.err.println(a);
        System.err.println(a > b);
        System.err.println(a > b ? 10 : 20);

        System.err.println(a != b);


    }
}
