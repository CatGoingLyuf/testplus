package com.example.multithreadDemo;

/**
 * @Author lyuf
 * @Date 2021/7/14 13:59
 * @Version 1.0
 */
public class TB {
    public static void main(String[] args) {
        TbDemo tb = new TbDemo();
        Thread t1 = new Thread(tb, "小明");
        Thread t2 = new Thread(tb, "小白");
        Thread t3 = new Thread(tb, "小红");
        Thread t4 = new Thread(tb, "小黑");
        Thread t5 = new Thread(tb, "小华");
        System.out.println("start");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
class TbDemo implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "进入run方法");
        //线程同步代码块
        //synchronized(obj){
        //}
        say();//调用同步方法
    }
   //同步方法
    public synchronized void say(){
        System.out.println(Thread.currentThread().getName() + "正在通话中。。。");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "通话结束！");
    }
}