package com.example.iodemo;

import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;
/**
 * @Author lyuf
 * @Date 2021/7/12 10:51
 * @Version 1.0
 */
public class demoio {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //文件创建
        File file = new File("D:/test/a.txt");
        if (file.exists()) {
            System.out.println("文件已存在");
            System.out.println("文件的绝对路径：" + file.getAbsolutePath());
            System.out.println("文件大小：" + file.length());
        } else {
            try {
                file.createNewFile();
                System.out.println("文件创建成功");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //数据输入
        int[] ints = new int[]{1, 2, 3, 4, 5, 6};
        byte[] bytes = new byte[ints.length];
//        for (int i = 0; i < bytes.length; i++) {
//            bytes[i] = (byte) ints[i];
//        }
        System.out.println("请输入内容：回车确定");
        String i = scanner.nextLine();
        try {
            //字节流效率较低
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(i.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
            //FileOutputStream是字节流，将文本按字节写入文件，而一个汉字是两个字节，无法一次写入，就会出现乱码
            //解决方法是使用OutputStreamWriter将字节流转换为字符流写入，同时指定utf-8编码
//            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), "utf-8");
//            outputStreamWriter.append("测试222");
//            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
