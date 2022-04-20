package com.example.iodemo;

import java.io.*;
/**
 * @Author lyuf
 * @Date 2021/7/12 14:30
 * @Version 1.0
 */
public class iodemo1 {
    public static void main(String[] args) throws IOException {
        File file = new File("D:/test/b.txt");
        File file2 = new File("D:/test/b2.txt");

        //字节流写入/输出
        FileOutputStream fileOutputStream = new FileOutputStream(file);
     //   String s = "打发士大夫拉萨的克里夫上帝发誓";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000000; i++){
            sb.append("打发士大夫拉萨的克里夫上帝发誓");
        }
        byte[] bytes2 = sb.toString().getBytes();

        long start = System.currentTimeMillis();
        fileOutputStream.write(bytes2);
        long end = System.currentTimeMillis();

        System.out.println("字节流耗时：" + (end - start));
        System.out.println("文件大小:" + file.length());
        fileOutputStream.close();
        //字节流读取/输入
        FileInputStream fileInputStream = new FileInputStream(file);
        //一次读取多少字节
        byte[] bytes = new byte[1024];
        //用来缓存读取的字节
        StringBuilder stringBuilder = new StringBuilder();
        //读取到字节的长度，为-1时表示没有数据
        int length = 0;
        //循环读取数据
        while ((length = fileInputStream.read(bytes)) != -1){
            //将读取到的内容转化为字符串
            stringBuilder.append(new String(bytes,0,length));
        }
        fileInputStream.close();
     //   System.out.println(stringBuilder);

        //缓冲字节流写入/输出    true追加读写
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
        long start2 = System.currentTimeMillis();
        bufferedOutputStream.write(bytes2);
        long end2 = System.currentTimeMillis();
        System.out.println("缓冲字节流耗时：" + (end2 - start2));
        System.out.println("文件大小:" + file2.length());
        bufferedOutputStream.close();
        //缓冲字节流读取/输入
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file2));
        //每次读取的字节
        byte[] bytes1 = new byte[1024];
        //用来缓冲读取的字节
        StringBuilder stringBuilder1 = new StringBuilder();
        //读取字节长度，-1表示没有数据
        int length2 = 0;
        //循环读取
        while ((length2 = bufferedInputStream.read(bytes)) != -1){
            stringBuilder1.append(new String(bytes,0,length2));
        }
        bufferedInputStream.close();
     //   System.err.println(stringBuilder1);


    }
}
