package com.example;

public class demo1 {
    public static void main(String[] args) {
        //八大基础类型
        //整型
        byte b;
        byte n1 = 12;
        byte n12 = 12;
        b = (byte) ( n1 + n12);

        short n2 = 12700;
        int n3 = 10;
        long n4 = 18789L;
        long n42 = 18789;
        double l = n4 / 1024;
        System.err.println(l);
        System.out.println(b);
        System.out.println(n1 + n2);
        System.out.println(n3 + n4);


        //浮点数
        float n5 = 50.1f;
        float n52 = 11.11f;
        double n6 = 11.11;
        double n62 = 50.1;
        double c = (double) n5;
        System.out.println(c);
        System.out.println(n5 + n52);
        System.out.println(n6 + n62);

        System.out.println(n5 + n6);

        //字符
        char a1 = '1';
        char a12 = '2';
//        char a2 = '123';
        char a6 = 123;
        char a3 = '中';
        char a32 = '国' ;
//        char a4 = 'abc';
        char a5 = 'a';
        char a52 = 'b';
        char m = 'a' + 'b';
        int m2 =  a3 + a32;
        //布尔
        boolean flag = true;

        System.out.println(m2);

        // byte,short,char在运算时是自动转换为int型的
        // 而int与long运算时自动转换为long型
        // float与double运算时是自动转换为double再进行计算的
        // int与float运算时先转换为float再运算。

        String aa = "3";
        System.out.println(1 + 2 + aa + 4);
        System.out.println(1 + aa + 2 + 4);

    }
}
