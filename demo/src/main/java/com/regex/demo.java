package com.regex;

import org.junit.Test;

/**
 * @Author lyuf
 * @Date 2022/1/14 15:45
 * @Version 1.0
 */
public class demo {

    @Test
    public void test(){
        String a = "1.1.1.0";
        boolean b = RegexUtils.checkIpAddress(a);
//        boolean b = RegexUtils.checkOsName(a);
        System.out.println(b);
    }


    @Test
    public void test2(){
        Long a = 5L;
        for (int i = 1; i <= a; i++) {
            System.out.println(i);
        }
    }
}
