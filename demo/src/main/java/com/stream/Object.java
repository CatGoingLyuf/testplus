package com.stream;

import lombok.Data;

/**
 * @Author lyuf
 * @Date 2022/4/29 9:30
 * @Version 1.0
 */
@Data
public class Object {

    private String peopleName;
    private String born;
    private String age;

    public Object(User user) {
        this.peopleName = user.getName();
        this.age = user.getAge();
        this.born = (2022L - Long.parseLong(user.getAge())) + "年";
    }
}
