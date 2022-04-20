package com.example.multithreadDemo.Demo;

import com.example.fandemo.Generator;

/**
 * @Author lyuf
 * @Date 2021/7/14 10:43
 * @Version 1.0
 */
public class GuessANumber extends Thread {
    private int number;

    public GuessANumber(int number) {
        this.number = number;
    }

    public void run(){
        int counter = 0;
        int guess = 0;
        do {
            guess = (int) (Math.random() * 10 + 1);
            System.out.println(this.getName() + "猜数字为" + guess);
            counter++;
        } while (guess != number);
        System.out.println("** 正确! " + this.getName()
                + " 猜了 " + counter + " 次数字 **");
    }
}
