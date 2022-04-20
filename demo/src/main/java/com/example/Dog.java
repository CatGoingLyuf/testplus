package com.example;

public class Dog {
    //属性封装
    private String name;
    private String color;
    private int age;
    private String health;

    public Dog() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", age=" + age +
                ", health='" + health + '\'' +
                '}';
    }

    public Dog(String name, String color, int age, String health) {
        this.name = name;
        this.color = color;
        this.age = age;
        this.health = health;
    }

    public void print(){
        System.out.println("狗名字:" + this.getName() + "-狗年龄：" + this.getAge() + "-狗健康：" + this.getHealth() + "-狗颜色:" + this.getColor());
    }
    public void call(){
        String call = "汪汪";
        System.out.println(call);
    }
}
