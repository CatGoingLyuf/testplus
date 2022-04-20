package com.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lyuf
 * @Date 2022/3/10 10:18
 * @Version 1.0
 */
public class test {

    @Test
    public void tet() {
        List<String> list2 = new ArrayList<String>() {{
            this.add("ccc");
            this.add("ccc");
            this.add("aaa");
            this.add("bbb");
        }};

        list2.stream().anyMatch(s -> {
            System.out.println(s);
            if (s.equals("aaa")) {
                return true;
            }
            return false;
        });
//        list2.stream().forEach(s -> {
//            System.out.println(s);
//        });

    }
}
