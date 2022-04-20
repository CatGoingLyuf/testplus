package com.example.multithreadDemo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author lyuf
 * @Date 2021/7/14 13:42
 * @Version 1.0
 */
public class CallableDemo implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        int i = 0;
        for (i = 0; i < 100; i++){
            System.out.println(Thread.currentThread().getName() + "" + i);
        }
        return i;
    }

    public static void main(String[] args) {
        //实例化callable类
        CallableDemo callableDemo = new CallableDemo();
        //用futureTask来包装callable对象
        FutureTask<Integer> integerFutureTask = new FutureTask<>(callableDemo);

        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "的循环变量i的值" + i);
            if (i == 20) {
                //使用 FutureTask 对象作为 Thread 对象的 target 创建并启动新线程
                new Thread(integerFutureTask,"有返回值的线程").start();
            }
        }
        try {
            System.out.println("返回值为：" + integerFutureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
