package com.example.iodemo;

import java.io.*;
import java.util.Scanner;

/**
 * @Author lyuf
 * @Date 2021/7/12 16:04
 * @Version 1.0
 */
public class iodemo3 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        File file = new File("D:/test/d.txt");
        Student s1 = new Student("bob", 21, "金融", "北大");
        Student s2 = new Student("jick", 19, "计算机", "清华");
        Student s3 = new Student("mical", 26, "金融", "浙大");
        Student s4 = new Student("track", 24, "金融", "哈弗");

        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);


        if (file.exists()){
            System.out.println("文件已存在,进行输入");
        } else {
            file.createNewFile();
            System.out.println("创建成功");
        }

        objectOutputStream.writeObject(s1.toString());
        objectOutputStream.writeObject(s2.toString());
        objectOutputStream.writeObject(s3.toString());
        objectOutputStream.writeObject(s4.toString());
        objectOutputStream.close();

        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);

//       Student o1 = (Student) objectInputStream.readObject();
//       Student o2 = (Student) objectInputStream.readObject();
//       Student o3 = (Student) objectInputStream.readObject();
//       Student o4 = (Student) objectInputStream.readObject();
       objectInputStream.close();
//       System.out.println("" + o1 + o2 + o3 + o4);


        // 从键盘输入0~25的整数
//        int a = -1;
//        Scanner sc = new Scanner(System.in);
//        while(a<0 | a>25){
//            System.out.println("从键盘输入一个0-25的整数:");
//            a = sc.nextInt();
//        }
//        // 随机访问文件中的字符
//        try{
//            RandomAccessFile inFile = new RandomAccessFile("D:/test/d.txt", "r");
//            inFile.seek(a); 				// 将文件指针移动到整数a的位置
//            char c = (char)inFile.read();	// 在inFile中读取一个字符
//            inFile.close();
//            System.out.println("文件中第"+a+"个字符是"+c);
//        }catch(IOException e){
//            System.out.println(e);
//        }


    }
}
