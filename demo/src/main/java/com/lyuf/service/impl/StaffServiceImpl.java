package com.lyuf.service.impl;

import com.lyuf.dao.impl.StaffDaoImpl;
import com.lyuf.pojo.Staff;
import com.lyuf.service.StaffService;
import jdk.nashorn.internal.ir.CallNode;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * @Author lyuf
 * @Date 2021/7/15 16:34
 * @Version 1.0
 */
public class StaffServiceImpl implements StaffService {

    private StaffDaoImpl staffDao = new StaffDaoImpl();

    private Scanner scanner = new Scanner(System.in);

    @Override
    public void addStaff() throws SQLException {
        System.out.println("请输入职员的姓名:");
        String name = scanner.nextLine();

        System.out.println("请输入职员的性别:");
        String gander = scanner.nextLine();

        System.out.println("请输入职员的年龄:");
        int age = scanner.nextInt();

        System.out.println("请输入职员的工资:");
        int salary = scanner.nextInt();

        Staff staff = new Staff(name, age, gander, salary);

        staffDao.addStaff(staff);
        Staff staffByName = staffDao.findStaffByName(name);
        System.out.println("已添加用户：" + staffByName);
    }

    @Override
    public void deleteStaffById() throws SQLException {
        System.out.println("请输入职员的ID:");
        int id = scanner.nextInt();
        staffDao.deleteStaffById(id);

        System.out.println("删除成功");
    }

    @Override
    public void findStaff() throws SQLException {
        System.out.println("请输入职员的ID:");
        int id = scanner.nextInt();
        Staff staff = staffDao.findStaff(id);
        System.out.println(staff);
    }

    @Override
    public void updateStaff() {


    }

    @Override
    public void findAll() throws SQLException {
        List<Staff> all = staffDao.findAll();
        all.stream().forEach(System.out::println);
    }
}
