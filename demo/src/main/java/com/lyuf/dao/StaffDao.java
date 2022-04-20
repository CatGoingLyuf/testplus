package com.lyuf.dao;

import com.lyuf.pojo.Staff;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author lyuf
 * @Date 2021/7/15 16:32
 * @Version 1.0
 */
public interface StaffDao {

    int addStaff(Staff staff) throws SQLException;

    int deleteStaff(Staff staff);

    int updateStaff(Staff staff);

    Staff findStaff(int id) throws SQLException;

    List<Staff> findAll() throws SQLException;

}
