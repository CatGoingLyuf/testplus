package com.lyuf;

import com.lyuf.controller.StaffController;
import org.springframework.stereotype.Controller;

import java.lang.reflect.InvocationTargetException;

/**
 * @Author lyuf
 * @Date 2021/7/15 16:29
 * @Version 1.0
 */
public class runProject {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        new StaffController().controller();

    }
}
