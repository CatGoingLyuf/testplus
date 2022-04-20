package com.lyuf.service;

import java.sql.SQLException;

/**
 * @Author lyuf
 * @Date 2021/7/15 16:33
 * @Version 1.0
 */
public interface StaffService {

    void addStaff() throws SQLException;

    void deleteStaffById() throws SQLException;

    void findStaff() throws SQLException;

    void updateStaff();

    void findAll() throws SQLException;

}
