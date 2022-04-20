package com.example.fandemo;

import sun.rmi.runtime.Log;

/**
 * @Author lyuf
 * @Date 2021/7/13 9:24
 * @Version 1.0
 */
//泛型类
//在实例化泛型类时，必须指定T的具体类型
public class Generic<T> {
    //key这个成员变量的类型为T，T的类型由外部指定
    private T key;

    public Generic(T key) {
        this.key = key;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public static void main(String[] args) {

        Generic<Integer> integerGeneric = new Generic<>(123);
        Generic<String> stringGeneric = new Generic<>("Key value");
        System.out.println(integerGeneric.getKey());
        System.out.println(stringGeneric.getKey());

        Generic generic = new Generic("111111");
        Generic generic1 = new Generic(4444);
        Generic generic2 = new Generic(55.55);
        Generic generic3 = new Generic(false);
        System.out.println("" + generic + generic1 + generic2 + generic3);




    }
}
