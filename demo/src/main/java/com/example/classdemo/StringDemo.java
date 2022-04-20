package com.example.classdemo;

/**
 * @Author lyuf
 * @Date 2021/7/13 11:02
 * @Version 1.0
 */
public class StringDemo {
    public static void main(String[] args) {
        String str = "A,B,C,D,E,F";
        int len = str.length();
        System.out.println( "str的长度是 : " + len );

        String string1 = "这是：";
        System.out.println("1、" + string1 + "连接字符串");

        float floatVar = 2.12F;
        Integer intVar = 32;
        String stringVar = "A,B,C,D,E,F";
        //输出格式化数字可以用printf 和 format方法，返回一个String 对象而不是 PrintStream 对象
        //format() 能用来创建可复用的格式化字符串，而不仅仅是用于一次打印输出
        str = String.format("浮点型变量的的值为 " +
                "%f, 整型变量的值为 " +
                " %d, 字符串变量的值为 " +
                " %s", floatVar, intVar, stringVar);

        System.out.println(str);
        System.out.println("" + floatVar + intVar + stringVar);
    }
}
