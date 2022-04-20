package com.example.iodemo;
import java.io.*;
/**
 * @Author lyuf
 * @Date 2021/7/12 15:14
 * @Version 1.0
 */
public class iodemo2 {
    public static void main(String[] args) throws IOException {
        File file = new File("D:/test/c.txt");
        File file2 = new File("D:/test/c2.txt");
        String s = "撒地方就拉省的";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000000; i++){
           sb.append("撒地方就拉省的");
        }
        String string = sb.toString();
        //字符流写入
        //OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file));
        //outputStreamWriter.write(s);
        //outputStreamWriter.close();

        FileWriter fileWriter = new FileWriter(file);
        long start = System.currentTimeMillis();
        fileWriter.write(string);
        long end = System.currentTimeMillis();
        System.out.println("字符流耗时：" + (end - start));
        System.out.println("文件大小:" + file.length());
        fileWriter.flush();
        fileWriter.close();
        //字符流的读取
//        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file));
//        char[] character = new char[1024];
//        int length = 0;
//        StringBuilder stringBuilder = new StringBuilder();
//        while ((length = inputStreamReader.read(character)) != -1){
//            stringBuilder.append(character,0,length);
//        }
//        inputStreamReader.close();
//        System.out.println(stringBuilder);
        FileReader fileReader = new FileReader(file);
        char[] chars = new char[1024];
        int l  = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while ((l = fileReader.read(chars)) != -1){
            stringBuilder.append(chars,0,l);
        }
        fileReader.close();
    //    System.out.println(stringBuilder);


        //缓冲字符流
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file2));
        long l1 = System.currentTimeMillis();
        bufferedWriter.write(string);
        long l2 = System.currentTimeMillis();
        System.out.println("缓冲字符流耗时：" + (l2 - l1));
        System.out.println("文件大小:" + file2.length());
        bufferedWriter.flush();
        bufferedWriter.close();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file2));
        char[] chars1 = new char[1024];
        int length = 0;
        StringBuilder stringBuilder1 = new StringBuilder();
        while ((length = bufferedReader.read(chars1)) != -1){
            stringBuilder1.append(chars1,0,length);
        }
        bufferedReader.close();
     //   System.err.println(stringBuilder1);

    }
}
