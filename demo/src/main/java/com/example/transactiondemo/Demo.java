package com.example.transactiondemo;

import com.example.transactiondemo.service.AccountService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author lyuf
 * @Date 2021/7/20 9:39
 * @Version 1.0
 */
public class Demo {

    @Test
    public void test(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        AccountService accountService = (AccountService) classPathXmlApplicationContext.getBean("accountService");
        accountService.transfer(1,2,100d);
    }
}
