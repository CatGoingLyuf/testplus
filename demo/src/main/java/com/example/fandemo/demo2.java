package com.example.fandemo;

/**
 * @Author lyuf
 * @Date 2021/7/13 10:27
 * @Version 1.0
 */
public class demo2 {
    //泛型方法 printArray
    public static <E> void printArray(E[] inputArray) {
        //循环输出数组
        for (E element : inputArray){
            //printf :"%d"的意思是一个int值的占位符
            //"%f"为一个double 或float值的点位符
            //"%s"是一个字符串值的点位符
            //"%c"是一个字符值的点位符
            System.out.printf("%s", element);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //创建不同的数组
        Integer[] intArray = { 1, 2, 3, 4, 5 };
        Double[] doubleArray = { 1.1, 2.2, 3.3, 4.4 };
        Character[] charArray = { 'H', 'E', 'L', 'L', 'O' };
        System.out.println("整形数组：");
        printArray(intArray);
        System.out.println("\n 双精度型数组：");
        printArray(doubleArray);
        System.out.println("\n 字符型数组：");
        printArray(charArray);
    }
}
