package com.example.transactiondemo.dao;

/**
 * @Author lyuf
 * @Date 2021/7/20 9:29
 * @Version 1.0
 */
public interface AccountDao {

    //加钱
    void increaseMoney(Integer id,Double money);
    //减钱
    void decreaseMoney(Integer id,Double money);
}
