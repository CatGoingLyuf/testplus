package com.lyuf.controller;

import com.lyuf.pojo.Staff;
import com.lyuf.service.impl.StaffServiceImpl;
import jdk.nashorn.internal.ir.CallNode;
import sun.dc.pr.PRError;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

/**
 * @Author lyuf
 * @Date 2021/7/15 16:29
 * @Version 1.0
 */
public class StaffController {
    private Scanner sc = new Scanner(System.in);
    private StaffServiceImpl staffService = new StaffServiceImpl();

    public void controller() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        while (true) {
            System.out.println("1. 添加职员 ");
            System.out.println("2. 开除指定职员 ");
            System.out.println("3. 修改指定职员 ");
            System.out.println("4. 查询指定职员 ");
            System.out.println("5. 查询所有职员 ");
            System.out.println("6. 过滤条件展示职员信息 ");
            System.out.println("7. 按照指定条件展示职员信息 ");
            System.out.println("8. 退出");

            int choose = sc.nextInt();
            sc.nextLine();

            if (8 == choose) {
                break;
            }

            // 获取所有枚举选择数组
            MethodEnum[] values = MethodEnum.values();

            // 遍历整个枚举内容
            for (MethodEnum value : values) {
                // 比对枚举中的choose内容是否和用户输入的一致
                if (choose == value.getChoose()) {
                    // 获取对应的方法名字taff
                    String methodName = value.getMethodName();

                    // 通过反射找到对应方法，执行方法操作
                    StaffServiceImpl.class.getMethod(methodName).invoke(staffService);
                }
            }
        }
    }

}
 enum MethodEnum {
    ADD_STUDENT(1, "addStaff"),
    REMOVE_STUDENT(2, "deleteStaffById"),
    FIND_ONE_STUDENT(3, "updateStaff"),
    MODIFY_STUDENT(4, "findStaff"),
    FIND_ALL_STUDENT(5, "findAll"),
    FILTER_STUDENT(6, "filterStudent"),
    SORT_STUDENT(7, "sortStudent");

    private int choose;
    private String methodName;

    MethodEnum() {}

    MethodEnum(int choose, String methodName) {
        this.choose = choose;
        this.methodName = methodName;
    }

    public int getChoose() {
        return choose;
    }

    public void setChoose(int choose) {
        this.choose = choose;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
