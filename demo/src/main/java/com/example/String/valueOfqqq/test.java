package com.example.String.valueOfqqq;

import org.junit.Test;
import scala.Int;

import java.util.HashMap;

/**
 * @Author lyuf
 * @Date 2022/4/24 11:05
 * @Version 1.0
 */
public class test {

    @Test
    public void test() {
        HashMap<String, String> map = new HashMap<>();
        String a = "1==";
        // toString 空指针
        String b = map.get("1").toString();
        // String.valueOf "null"
        String c = String.valueOf(map.get("1"));
        if (b != null) {
            System.out.println(a.concat(String.valueOf(b)));
        }
    }

    @Test
    public void test2() {
        HashMap<String, String> map = new HashMap<>();
        char[] chars = {'1',1,'c'};
        String s = String.copyValueOf(chars);
        String intern = s.intern();
        String s2 = String.valueOf(chars);
//        System.out.println(System.identityHashCode(chars));
//        System.out.println(System.identityHashCode(s));
//        System.out.println(System.identityHashCode(s2));
        // 返回一个字符串，内容与此字符串相同，若常量池里有相同字符串则返回池中字符串的引用，若没有则将其复制一份添加进池中再返回池中字符串的引用
//        System.out.println(System.identityHashCode(intern));

        int a = Integer.MAX_VALUE;
        int b = Integer.MIN_VALUE;
        System.out.println(a + "" +b);
    }
}
