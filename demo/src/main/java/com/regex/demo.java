package com.regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author lyuf
 * @Date 2022/1/14 15:45
 * @Version 1.0
 */
public class demo {

    @Test
    public void test() {
        String a = "1.1.1.0";
        boolean b = RegexUtils.checkIpAddress(a);
//        boolean b = RegexUtils.checkOsName(a);
        System.out.println(b);
    }


    @Test
    public void find() {
        String a = "sdfkkljsafdljasdjklfjlasd";
        Pattern sdf = Pattern.compile("sdf");
        Matcher matcher = sdf.matcher(a);
        System.out.println(matcher.find());
    }

    @Test
    public void group() {
        String str = "时间段:12:00-00:00,电费:70.0";
        // \d*\.?\d*
        Pattern pattern = Pattern.compile("时间段:()-(),电费:()");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println("matcher.group(0) " + matcher.group(0));//得到第0组——整个匹配
            System.out.println("matcher.group(1) " + matcher.group(1));//得到第一组与第一个括号中对应中值匹配
            System.out.println("matcher.group(2) " + matcher.group(2));//得到第二组与第二个括号中对应中值匹配
            System.out.println("matcher.group(3) " + matcher.group(3));//得到第三组与第三个括号中对应中值匹配
            //以此类推
        }
        // 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 24 26
        // A B C D E F G H I  J  K  L  M  N  O  P  Q  R  S  T  U  V  W  X  Y  Z
        // 9D 8F 3E 6C
         //       94  86  35  63
           //     id hf ce fc
    }
}
