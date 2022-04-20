package com.example.fandemo;

import java.util.Random;

/**
 * @Author lyuf
 * @Date 2021/7/13 9:54
 * @Version 1.0
 */
public class FruitGenerator<T> implements Generator<String> {

    private String[] fruits = new String[]{"Apple", "Banana", "Pear"};

    @Override
    public String next() {
        Random random = new Random();
        String fruit = fruits[random.nextInt(3)];
        System.out.println(fruit);
        return fruit;
    }


    public static void main(String[] args) {
        FruitGenerator<Object> objectFruitGenerator = new FruitGenerator<>();
        objectFruitGenerator.next();
    }
}
