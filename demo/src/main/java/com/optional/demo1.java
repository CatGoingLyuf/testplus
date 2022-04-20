package com.optional;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author lyuf
 * @Date 2022/1/4 14:01
 * @Version 1.0
 */
public class demo1 {

    @Test
    public void getName() {
//        Cat cat = new Cat("bob","11",null,null);
        Cat cat = new Cat();
        if (cat.getName() == null) {
            System.out.println("UnKnow");
        } else System.out.println(cat.getName());

    }

    @Test
    public void test() {
        Cat cat = new Cat("bob", "11", "", null);
        Cat cat2 = new Cat();


        System.out.println(
                Optional.ofNullable(cat.getName()).isPresent()
        );

        System.out.println(StringUtils.isEmpty(cat.getColor()));
        System.out.println(StringUtils.isNotEmpty(cat.getLight()));

        System.out.println(
                Optional.ofNullable(cat).map(cat1 -> cat1.getColor()).orElse("unknow")
        );
        System.out.println(
                Optional.ofNullable(cat2).map(cat1 -> cat1.getName()).orElseGet(() -> cat.getName())
        );
        System.out.println(
                Optional.ofNullable(cat).map(cat1 -> cat1.getName()).orElseThrow(() -> new DateTimeException("aaaaa"))
        );

        System.out.println(
                Optional.ofNullable(cat).map(c -> c.getName())
        );

        System.out.println(Optional.ofNullable(cat).map(Cat::getName));
    }

    @Test
    public void test2() {

        Cat cat1 = new Cat("bob", "11", "", null);
        Cat cat2 = new Cat("aoa", "11", "", null);
        Cat cat3 = new Cat("coc", "11", "", null);

        ArrayList<Cat> strings = new ArrayList<>();
        strings.add(cat1);
        strings.add(cat2);
        strings.add(cat3);

        List<String> collect = strings.stream().map(Cat::getName).collect(Collectors.toList());
        System.out.println(collect);

    }

}
