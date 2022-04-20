package com.example.multithreadDemo;

/**
 * @Author lyuf
 * @Date 2021/7/14 15:04
 * @Version 1.0
 */
public class SiSuoDemo {
    public static void main(String[] args) {

        new Ss();
    }
}


class A {
    //同步方法
    public synchronized void say(B b) {
        System.out.println("a说你先把枪放下，我再放下枪");
        b.dos();
    }

    public synchronized void dos() {
        System.out.println("同意b要求，放下枪");
    }
}


class B {
    //同步方法
    public synchronized void say(A a) {
        System.out.println("b说你先把枪放下，我再放下枪");
        a.dos();
    }

    public synchronized void dos() {
        System.out.println("同意a要求，放下枪");
    }
}

class Ss implements Runnable {
    A a = new A();
    B b = new B();

    //线程死锁
    public Ss() {
        Thread thread = new Thread(this);
        thread.start();//线程就绪
        System.out.println(Thread.currentThread().getName());//main
        b.say(a);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        a.say(b);
    }
}