package com.aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author lyuf
 * @Date 2021/7/19 15:49
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:ApplicationContext.xml"})

public class aopTest {
//    @Resource(name ="aService" )
    @Autowired
    @Qualifier(value = "aService")
    JDKServiceImpl jdkService;

    @Autowired
    @Qualifier(value = "bService")
    CGLibServiceImpl cgLibService;


    @Test
    public void test() {
        jdkService.barA();
        jdkService.fooA("aaa");
    }
}
