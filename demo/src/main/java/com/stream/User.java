package com.stream;

import lombok.Data;

/**
 * @Author lyuf
 * @Date 2022/4/29 9:30
 * @Version 1.0
 */
@Data
public class User {

    private String name;
    private String age;
    private String max;
    private String min;

    public User(String name, String age, String max, String min) {
        this.name = name;
        this.age = age;
        this.max = max;
        this.min = min;
    }
}
