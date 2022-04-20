package com.example.classdemo;

/**
 * @Author lyuf
 * @Date 2021/7/13 11:54
 * @Version 1.0
 */
public class NumberAndMathDemo {
    public static void main(String[] args) {
        // Java 语言为每一个内置数据类型提供了对应的包装类
        // 所有的包装类（Integer、Long、Byte、Double、Float、Short）
        // 都是抽象类 Number 的子类
        Integer x = 5;
        x = x + 10;
        System.out.println(x);

        // Math 包含了用于执行基本数学运算的属性和方法
        // 如初等指数、对数、平方根和三角函数
        // Math 的方法都被定义为 static 形式
        // 通过 Math 类可以在主函数中直接调用 JAVA
        System.out.println("90度的正弦值：" + Math.sin(Math.PI/2));
        System.out.println("0度的余弦值：" + Math.cos(0));
        System.out.println("60度的正切值：" + Math.tan(Math.PI/3));
        System.out.println("1的反正切值： " + Math.atan(1));
        System.out.println("π/2的角度值：" + Math.toDegrees(Math.PI/2));
        System.out.println(Math.PI);
        System.out.println("随机数：" + Math.random());
    }
}
