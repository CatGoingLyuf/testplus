package com.example.classdemo;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

/**
 * @Author lyuf
 * @Date 2021/7/13 11:07
 * @Version 1.0
 */
public class SystemDemo {
    //System 类包含一些与系统相关的类字段和方法
    // 它不能被实例化,类中所有属性和方法都是static,可直接被System调用

    public static void main(String[] args) {

        //数组拷贝
        //src：源数组
        //srcPos：原数组中的起始位置
        //dest：目标数组
        //destPos：目标数组中的起始位置
        //length：要复制的数组元素的数量
        int[] src = {1,2,3,4,5,6,7,8,9};
        int[] dest = new int[20];
        System.arraycopy(src,0,dest,0,8);
        System.out.println(Arrays.toString(dest));

        //返回以毫秒为单位的当前时间
        long l = System.currentTimeMillis();
        System.out.println(l);

        //获得指定的环境变量
        String s = "123";
        String getenv = System.getenv(s);
        System.out.println(getenv);

        //运行垃圾回收器
        System.gc();

        //取得当前的系统属性
        Properties properties = System.getProperties();
        System.out.println(properties);

        //取得指定键指示的系统属性
        System.out.println("jdk版本号：" + System.getProperty("java.version")); // jdk版本号
        System.out.println("Java提供商名称：" + System.getProperty("java.vendor")); // Java提供商名称
        System.out.println("Java提供商网站：" + System.getProperty("java.vendor.url")); // Java提供商网站
        System.out.println("jre目录：" + System.getProperty("java.home")); // jre目录
        System.out.println("Java虚拟机规范版本号：" + System.getProperty("java.vm.specification.version")); // Java虚拟机规范版本号
        System.out.println("Java虚拟机规范提供商：" + System.getProperty("java.vm.specification.vendor")); // Java虚拟机规范提供商
        System.out.println("Java虚拟机规范名称：" + System.getProperty("java.vm.specification.name")); // Java虚拟机规范名称
        System.out.println("Java虚拟机版本号：" + System.getProperty("java.vm.version")); // Java虚拟机版本号
        System.out.println("Java虚拟机提供商：" + System.getProperty("java.vm.vendor")); // Java虚拟机提供商
        System.out.println("Java虚拟机名称：" + System.getProperty("java.vm.name")); // Java虚拟机名称
        System.out.println("Java规范版本号：" + System.getProperty("java.specification.version")); // Java规范版本号
        System.out.println("Java规范提供商：" + System.getProperty("java.specification.vendor")); // Java规范提供商
        System.out.println("Java规范名称：" + System.getProperty("java.specification.name")); // Java规范名称
        System.out.println("Java类版本号：" + System.getProperty("java.class.version")); // Java类版本号
        System.out.println("Java类路径：" + System.getProperty("java.class.path")); // Java类路径
        System.out.println("Java lib路径：" + System.getProperty("java.library.path")); // Java lib路径
        System.out.println("Java输入输出临时路径：" + System.getProperty("java.io.tmpdir")); // Java输入输出临时路径
        System.out.println("Java编译器：" + System.getProperty("java.compiler")); // Java编译器
        System.out.println("Java执行路径：" + System.getProperty("java.ext.dirs")); // Java执行路径
        System.out.println("操作系统名称：" + System.getProperty("os.name")); // 操作系统名称
        System.out.println("操作系统的架构：" + System.getProperty("os.arch")); // 操作系统的架构
        System.out.println("操作系统版本号：" + System.getProperty("os.version")); // 操作系统版本号
        System.out.println("路径分隔符：" + System.getProperty("path.separator")); // 路径分隔符
        System.out.println("直线分隔符：" + System.getProperty("line.separator")); // 直线分隔符
        System.out.println("操作系统用户名：" + System.getProperty("user.name")); // 用户名
        System.out.println("操作系统用户的主目录：" + System.getProperty("user.home")); // 用户的主目录
        System.out.println("当前程序所在目录：" + System.getProperty("user.dir")); // 当前程序所在目录


        //终止虚拟机的运行.对于发生了异常情况而想终止虚拟机的运行,传递一个非0数值,对于正常情况下退出系统传递0值;
        //该方法实际调用的是Runtime.getRuntime().exit(int status);
        System.exit(0);
    }
}
