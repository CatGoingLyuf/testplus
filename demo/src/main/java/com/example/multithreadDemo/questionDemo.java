package com.example.multithreadDemo;

/**
 * @Author lyuf
 * @Date 2021/7/14 14:06
 * @Version 1.0
 */
public class questionDemo {
    public static void main(String[] args) throws InterruptedException {
        AddThread add = new AddThread();
        DecThread dec = new DecThread();

        add.start();
        dec.start();

        add.join();
        dec.join();

        System.out.println(Counter.count);
    }
}

class  Counter {
    public static final String lock = "synchronized";
    public static int count = 0;

    public static void add(int num){
        synchronized (Counter.lock) {
            count = count + num;
        }
    }
}

class AddThread extends Thread {
    public void  run() {
        for (int i = 0; i < 10000; i++) {
            synchronized (Counter.lock){
                Counter.count += 1;
            }
        }
    }
}

class DecThread extends Thread {
    public void run() {
        for (int i = 0; i < 10000; i++) {
            synchronized (Counter.lock){
                Counter.count -= 1;
            }
        }
    }
}

