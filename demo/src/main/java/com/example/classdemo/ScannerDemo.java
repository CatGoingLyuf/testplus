package com.example.classdemo;

import java.util.Scanner;

/**
 * @Author lyuf
 * @Date 2021/7/13 10:52
 * @Version 1.0
 */
public class ScannerDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入：");
        if (scanner.hasNext()) {
            // next(): 一定要读取到有效字符后才可以结束输入
            // 对输入有效字符之前遇到的空白，next() 方法会自动将其去掉
            // 只有输入有效字符后才将其后面输入的空白作为分隔符或者结束符
            // next() 不能得到带有空格的字符串
            String next = scanner.next();
            System.out.printf("输入数据为：%s", next);
        }
        if (scanner.hasNext()) {
            //nextLine(): 以Enter为结束符
            // 也就是说 nextLine()方法返回的是输入回车之前的所有字符
            // 可以获得空白
            String s = scanner.nextLine();
            System.out.printf("输入数据为： %s",s);
        }

    }
}
