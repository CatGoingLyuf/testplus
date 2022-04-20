package com.example.multithreadDemo.Demo;

/**
 * @Author lyuf
 * @Date 2021/7/14 10:41
 * @Version 1.0
 */
public class DisplayMessage implements Runnable {
    private String message;

    public DisplayMessage(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        while (true){
            System.out.println(message);
        }
    }
}
