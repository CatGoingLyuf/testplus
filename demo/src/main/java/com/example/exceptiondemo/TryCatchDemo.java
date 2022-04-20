package com.example.exceptiondemo;

/**
 * @Author lyuf
 * @Date 2021/7/13 14:34
 * @Version 1.0
 */
public class TryCatchDemo {
    public static void main(String[] args) {
        //可以在 try 语句后面添加任意数量的 catch 块。
        //如果保护代码中发生异常，异常被抛给第一个 catch 块。
        //如果抛出异常的数据类型与 ExceptionType1 匹配，它在这里就会被捕获。
        //如果不匹配，它会被传递给第二个 catch 块。
        //如此，直到异常被捕获或者通过所有的 catch 块。
        int a[] = new int[2];
        try {
            System.out.println(a[3]);
        } catch (UnsupportedOperationException e1) {
            System.out.println(e1);
        } catch (ClassCastException e) {
            System.out.println(e);
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e);
        }

        //finally关键字用来创建在try代码块后面执行的代码块。
        //无论是否发生异常，finally代码块中的代码总会被执行。
        try {
            System.out.println(a[3]);
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e);
        } finally {
            System.out.println("aaaaaaaaaaa");
        }
    }
}
