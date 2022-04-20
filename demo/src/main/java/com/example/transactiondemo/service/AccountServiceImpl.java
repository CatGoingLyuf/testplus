package com.example.transactiondemo.service;

import com.example.transactiondemo.dao.AccountDao;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author lyuf
 * @Date 2021/7/20 9:37
 * @Version 1.0
 */
public class AccountServiceImpl implements AccountService{

    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    @Transactional
    public void transfer(Integer from, Integer to, Double money) {
        //加钱
        accountDao.increaseMoney(to,money);
        //发生异常
        //int i = 1/0;
        //减钱
        accountDao.decreaseMoney(from,money);
    }
}
