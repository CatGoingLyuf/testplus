package com.example.multithreadDemo;

/**
 * @Author lyuf
 * @Date 2021/7/14 10:18
 * @Version 1.0
 */
public class ThreadDemo extends Thread {

    public void NewThread() {
        super.setName("Demo Thread");
        System.out.println("Child thread: " + this);
        start(); // 开始线程
    }

    public void run() {
        for (int i = 5; i > 0; i--) {
            System.out.println("Child Thread: " + i + Thread.currentThread());
            // 让线程休眠一会
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.out.println("Child interrupted.");
            }
        }
        System.out.println("Exiting child thread.");
    }
}

class MainExtendThread {
    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        threadDemo.NewThread();
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("Main Thread: " + i + Thread.currentThread());
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        System.out.println("Main thread exiting.");
    }
}