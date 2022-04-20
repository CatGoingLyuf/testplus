package com.example.classdemo;

import jdk.nashorn.internal.ir.CallNode;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author lyuf
 * @Date 2021/7/13 11:58
 * @Version 1.0
 */
public class DateDemo {
    public static void main(String[] args) {
        //Date 类来封装当前的日期和时间
        // Date 类提供两个构造函数来实例化 Date 对象
        //(1).构造函数使用当前日期和时间来初始化对象
        Date date = new Date();
        System.out.println(date);
        System.out.println(date.toString());
        //(2).构造函数接收一个参数，该参数是从1970年1月1日起的毫秒数
        Date date1 = new Date(0L);
        System.out.println(date1);

        //格式化
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd 'at' HH.mm.ss a zzz");
        System.out.println(simpleDateFormat.format(date));

        String format = String.format("显示时间：%tc", date);
        System.out.println(format);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -3);
        calendar.set(2017,9,1);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);

        System.out.println(year + "年" + month + "月" + day + "日");
    }
}
