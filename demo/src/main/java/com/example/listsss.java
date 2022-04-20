package com.example;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * @Author lyuf
 * @Date 2022/4/18 16:30
 * @Version 1.0
 */
public class listsss {
    @Test
    public void test() {
        ArrayList<Object> objects = new ArrayList<>();
        int arrayListCapacity = getArrayListCapacity(objects);
        System.out.println(arrayListCapacity);
//        objects.add("1");
//        int arrayListCapacity2 = getArrayListCapacity(objects);
//        System.out.println(arrayListCapacity2);
        for (int i = 1; i <= 11; i++) {
            objects.add(i);
        }
        int arrayListCapacity3 = getArrayListCapacity(objects);
        System.out.println(arrayListCapacity3);
    }

    public static int getArrayListCapacity(ArrayList<?> arrayList) {
        Class<ArrayList> arrayListClass = ArrayList.class;
        try {
            Field field = arrayListClass.getDeclaredField("elementData");
            field.setAccessible(true);
            Object[] objects = (Object[])field.get(arrayList);
            return objects.length;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return -1;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return -1;
        }
    }

}
