package com.example.exceptiondemo;

/**
 * @Author lyuf
 * @Date 2021/7/13 14:56
 * @Version 1.0
 */
public class demo {
    public static double getDivision (double a, double b)throws MyExceptionDemo{
        if(b == 0){
            throw new MyExceptionDemo("被除数不能为0");
        }else if(b<0){
            throw new MyExceptionDemo("被除数不能为负数");
        }else{
            return a/b;
        }
    }

    public static void main(String[] args) {
        double a = 10;
        double b = 0;

        try {
            double c = demo.getDivision(a,b);
        } catch (MyExceptionDemo myExceptionDemo) {
            myExceptionDemo.printStackTrace();
        }
    }
}
