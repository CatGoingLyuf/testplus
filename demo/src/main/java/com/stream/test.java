package com.stream;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;

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

    @Test
    public void test() {
        User aaa = new User("aaa", "16", "1", "10");
        User vvv = new User("vvv", "17", "1", "10");
        User bbb = new User("bbb", "18", "1", "10");
        User ddd = new User("ddd", "19", "1", "10");
        //此List与常用的List不同，它是Collections类里的静态内部类，在继承AbstractList后并没有实现add()、remove()等方法，所以返回的List不能进行增加和删除元素操作。
        List<User> userList = Collections.emptyList();
        List<User> list = new ArrayList<>(userList);
        list.add(aaa);
        list.add(vvv);
        list.add(bbb);
        list.add(ddd);

        HashSet<String> resSet = Sets.newHashSet();
        // 使用Lists.transform把对象list中的某个属性取出来  返回一个新的list 对于同一个List集合中，假定想获取User类中的String Age,String Name,两个属性的值情况的并集。
        resSet.addAll(Lists.transform(list, User::getAge));
        resSet.addAll(Lists.transform(list, User::getName));

        List<String> collect = resSet.stream().filter(Objects::nonNull).collect(Collectors.toList());
        // [aaa, bbb, 16, ddd, 17, 18, vvv, 19]
        System.out.println(collect);
    }

    @Test
    public void tesss() throws UnsupportedEncodingException {
        String a = "aaa-sdfasndfkasdfnasdj-asdf.xdl";
        int i = a.indexOf("-");
        String substring = a.substring(i + 1);
        System.out.println(substring);
    }

    @Test
    public void tessss() {
        User aaa = new User("aaa", "16", "1", "10");
        User vvv = new User("vvv", "17", "1", "10");
        User bbb = new User("bbb", "18", "1", "10");
        User ddd = new User("ddd", "19", "1", "10");
        List<User> userList = Collections.emptyList();
        List<User> list = new ArrayList<>(userList);
        list.add(aaa);
        list.add(vvv);
        list.add(bbb);
        list.add(ddd);
        // user 数据封装成 user2
        List<User2> collect = list.stream().map(User2::new).collect(Collectors.toList());
    }
}
