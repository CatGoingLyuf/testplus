package com.example;

public class WangCai extends Dog{

    public WangCai(String a, int i, String well, String blue){
    }

    public WangCai(String name, String color, int age, String health) {
        super(name, color, age, health);
    }

    public WangCai() {

    }


    @Override
    public void print() {
        super.setName("旺财");
        super.print();
        System.out.println("重写父类的方法");
    }

    @Override
    public void call() {
        super.call();
        System.out.println("call方法");
    }

}
