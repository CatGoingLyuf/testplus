package com.example.iodemo;

import java.io.Serializable;

/**
 * @Author lyuf
 * @Date 2021/7/12 16:11
 * @Version 1.0
 */
public class Student implements Serializable {
    String name;
    int age;
    String project;
    String school;

    public Student(String name, int age, String project, String school) {
        this.name = name;
        this.age = age;
        this.project = project;
        this.school = school;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", project='" + project + '\'' +
                ", school='" + school + '\'' +
                '}';
    }
}
