package com.example.multithreadDemo.Demo;

/**
 * @Author lyuf
 * @Date 2021/7/14 10:48
 * @Version 1.0
 */
public class ThreadClassDemo {
    public static void main(String[] args) {

        //线程1
        DisplayMessage displayMessage = new DisplayMessage("Hello");
        Thread thread = new Thread(displayMessage);
        //定义：守护线程–也称“服务线程”，在没有用户线程可服务时会自动离开。
        //优先级：守护线程的优先级比较低，用于为系统中的其它对象和线程提供服务。
        //设置：通过setDaemon(true)来设置线程为“守护线程”；将一个用户线程设置为
        //守护线程的方式是在 线程对象创建 之前 用线程对象的setDaemon方法。
        thread.setDaemon(true);
        thread.setName("zero");
        System.out.println("线程0start" + thread.getName());
        thread.start();

        Runnable bye = new DisplayMessage("bye");
        Thread thread1 = new Thread(bye);
        //线程级别 setPriority
        thread1.setPriority(Thread.MIN_PRIORITY);
        thread1.setDaemon(true);
        System.out.println("线程1start" + thread1.getName());
        thread1.start();

        //线程3
        Thread thread2 = new GuessANumber(5);
        System.out.println("线程3start" + thread2.getName());
        thread2.start();

        try {
            //join()方法将挂起调用线程的执行，直到被调用的对象完成它的执行
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted.");
        }
        System.out.println("Starting thread4...");
        Thread thread3 = new GuessANumber(6);
        thread3.start();
        System.out.println("ending...");
    }
}
