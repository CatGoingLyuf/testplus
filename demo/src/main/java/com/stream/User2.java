package com.stream;

import lombok.Data;

/**
 * @Author lyuf
 * @Date 2022/4/29 9:30
 * @Version 1.0
 */
@Data
public class User2 {

    private String name;
    private String age;
    private String max;

    public User2(User user) {
        this.name = user.getName();
        this.age = user.getAge();
        this.max = user.getMax();
    }
}
