package com.example.fandemo;

import sun.rmi.runtime.Log;

import java.util.ArrayList;

/**
 * @Author lyuf
 * @Date 2021/7/13 8:53
 * @Version 1.0
 */
public class demo1 {
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("aaaaa");
        arrayList.add(100);

        for (int i = 0; i < arrayList.size(); i++) {
            String item = (String) arrayList.get(i);
            System.out.println("泛型测试:item = " + item );
        }
    }
}
