package com.example.transactiondemo.service;

/**
 * @Author lyuf
 * @Date 2021/7/20 9:35
 * @Version 1.0
 */
public interface AccountService {

    //转账方法
    void transfer(Integer from, Integer to,Double money);

}
