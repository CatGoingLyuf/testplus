package com.lyuf.dao.impl;

import com.lyuf.BaseDao;
import com.lyuf.dao.StaffDao;
import com.lyuf.pojo.Staff;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author lyuf
 * @Date 2021/7/15 16:32
 * @Version 1.0
 */
public class StaffDaoImpl extends BaseDao implements StaffDao {
    @Override
    public int addStaff(Staff staff) throws SQLException {
        String sql = "insert into staff(name, age, gender, salary) VALUES (?,?,?,?);";
        Object[] parameters = {staff.getName(), staff.getAge(), staff.getGender(), staff.getSalary()};
        return super.update(sql,parameters);
    }

    @Override
    public int deleteStaff(Staff staff) {
        String sql = "delete from test.staff where name = ?";
        return 0;
    }

    public void deleteStaffById(int id) throws SQLException {
        String sql = "delete from test.staff where id = " + id;
        super.update(sql);
    }

    public Staff findStaffByName(String name) throws SQLException {
        String sql = "select * from test.staff where name = '" + name + " ' ";
        List<Staff> query = super.query(sql,Staff.class);

        return query != null ? query.get(0) : null;
    }

    @Override
    public int updateStaff(Staff staff) {

        return 0;
    }

    @Override
    public Staff findStaff(int id) throws SQLException {
        String sql = "select * from test.staff where id = " + id;
        List<Staff> query = super.query(sql, Staff.class);

        return query != null ? query.get(0) : null;
    }

    @Override
    public List<Staff> findAll() throws SQLException {
        String sql = "select * from test.staff ";
        List<Staff> query = super.query(sql, Staff.class);
        return query;
    }
}
