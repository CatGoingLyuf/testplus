package com.example.classdemo;

/**
 * @Author lyuf
 * @Date 2021/7/13 11:34
 * @Version 1.0
 */
public class StringBufferAndStringBuilderDemo {
    public static void main(String[] args) {
        //StringBuffer 和 StringBuilder 类和 String 类不同的是：
        // StringBuffer 和 StringBuilder 类的对象能够被多次的修改，并且不产生新的未使用对象
        // StringBuilder 类和 StringBuffer 之间的最大不同在于
        // StringBuilder 的方法不是线程安全的（不能同步访问）
        //由于 StringBuilder 相较于 StringBuffer 有速度优势
        // 所以多数情况下建议使用 StringBuilder 类
        StringBuffer sb = new StringBuffer("推荐:");
        sb.setCharAt(0,'奇');

        sb.append("使用");
        sb.append("StringBuffer类");

        int capacity = sb.capacity();
        System.out.println(capacity);
        System.out.println(sb.length());


        System.out.println(sb);
    }
}
