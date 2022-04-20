package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class demo4 {

    public static int GetRandomInt(int n1, int n2) {
        int result = (int) (n1 + Math.random() * (n2 - n1 + 1));
        return result;
    }

    public static char GetRandomChar(char c1, char c2) {
        char result = (char) (c1 + Math.random() * (c2 - c1 + 1));
        return result;
    }

    public static Integer[] GetRandomArray() {
        int n = 10;
        Random random = new Random();
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(100);
        }
        return arr;
    }

    public static ArrayList GetRandomArrayList() {
        int n = 10;
        Random random = new Random();
        ArrayList<Integer> arrayList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            arrayList.add(i, random.nextInt(100));
        }
        return arrayList;
    }

    public static HashMap GetRandomHashMap() {
        int n = 10;
        Random random = new Random();
        HashMap<Integer, Object> integerObjectHashMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            integerObjectHashMap.put(i, random.nextInt(100));
        }
        return integerObjectHashMap;
    }

    public static void main(String[] args) {
        int a = GetRandomInt(5, 25);
        int b = GetRandomInt(1, 20);
        Random random = new Random();
        int c = random.nextInt(5);
        //if  else
        System.out.println("if-else:");
        if (a > b) {
            System.out.println("a大，a的数值为:" + a);
        } else {
            System.out.println("b大，b的数值为：" + b);
        }
        // switch case
        System.out.println("switch-case:");
        switch (c) {
            case 1:
                System.out.println("c=1:" + c);
                break;
            case 2:
                System.out.println("c=2:" + c);
                break;
            case 3:
                System.out.println("c=3:" + c);
                break;
            case 4:
                System.out.println("c=4:" + c);
                break;
            default:
                System.out.println("c=0:" + c);
                return;
        }
        //while
        System.out.println("while:");
        while (true) {
            char d = GetRandomChar('a', 'e');
            if (d < 'c') {
                System.out.println("d小于c继续,d为:" + d);
                continue;
            }
            if (d > 'c') {
                System.out.println("d大于c结束,d为:" + d);
                break;
            }
        }
        //for
        System.out.println("for:");
        for (int i = GetRandomInt(10, 15); i < 15; i++) {
            for (int j = GetRandomInt(15, 20); j > 15; j--) {
                System.out.println(i);
                System.out.println(j);
            }
        }
        //foreach
        System.out.println("foreach:");
        Integer[] array = GetRandomArray();
        Arrays.sort(array);
        String arr = Arrays.toString(array);
        System.out.println(arr);
        for (int x : array) {
            System.out.println(x);
        }
        //return break continue
        /**
         * （1）return语句：是指结束该方法，继续执行方法后的语句。
         * java中break和continue可以跳出指定循环，break和continue之后不加任何循环名则默认跳出其所在的循环，在其后加指定循环名，则可以跳出该指定循环（指定循环一般为循环嵌套的外循环）。
         * （2）break语句：是指在循环中直接退出循环语句（for，while，do-while，foreach），break之后的循环体里面的语句也执行。
         * （3）continue语句：是指在循环中中断该次循环语句（for，while，do-while，foreach），本次循环体中的continue之后语句不执行，直接跳到下次循环。
         */
        System.out.println("return break continue:");
        while (true) {
            char e = GetRandomChar('a', 'd');
            if (e == 'a') {
                System.out.println("e等于a,continue,e为:" + e);
                continue;
            }
            if (e == 'b') {
                System.out.println("e等于b,break,e为:" + e);
                break;
            }
            if (e == 'c') {
                System.out.println("e等于c,return,e为:" + e);
                return;
            }
        }
    }
}
