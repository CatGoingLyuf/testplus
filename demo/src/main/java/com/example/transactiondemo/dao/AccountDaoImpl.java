package com.example.transactiondemo.dao;

import com.example.transactiondemo.dao.AccountDao;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * @Author lyuf
 * @Date 2021/7/20 9:31
 * @Version 1.0
 */
public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao {

    @Override
    public void increaseMoney(Integer id, Double money) {
        getJdbcTemplate().update("update account set money = money + ? where id = ? ",money,id);
    }

    @Override
    public void decreaseMoney(Integer id, Double money) {
        getJdbcTemplate().update("update account set money = money - ? where id = ?",money,id);
    }
}
