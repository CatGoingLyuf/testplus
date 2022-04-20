package com.example.multithreadDemo;


import sun.rmi.runtime.NewThreadAction;

/**
 * @Author lyuf
 * @Date 2021/7/14 9:35
 * @Version 1.0
 */
public class RunnableDemo implements Runnable {

    Thread t;

    public void NewThread() {
        //创建第二个新线程
        t = new Thread(this, "Demo Thread");
        //优先级低的并不代表一定要等到优先级高的运行完才能运行
        // 只是cpu分配的资源少了
        t.setPriority(10);
        System.out.println("子线程：" + t);
        t.start();//开始线程

    }

    @Override
    public void run() {
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("子线程：" + i);
                System.out.println(Thread.currentThread());
                //暂停线程
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("Child interrupted");
        }
        System.out.println("退出子线程");
    }
}

class MainThreadDemo {
    public static void main(String[] args) {
        RunnableDemo runnableDemo = new RunnableDemo();
        runnableDemo.NewThread();//创建一个新线程

        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("主线程: " + i);
                //线程名称, 线程优先级, 线程所属线程组
                System.out.println(Thread.currentThread());
                Thread.sleep(100);

            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        System.out.println("退出主线程");
    }
}

