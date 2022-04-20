package com.example.CGLibProxy;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author lyuf
 * @Date 2021/7/19 14:36
 * @Version 1.0
 */
public class CGLibProxy implements MethodInterceptor {

    private UserDaoImpl userDao;

    public CGLibProxy(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    public UserDao createProxy() {
        // 使用CGLIB生成代理:
        // 1.创建核心类:Enhancer允许为非接口类型创建一个Java代理。
        // Enhancer动态创建了给定类型的子类但是拦截了所有的方法。
        // 和Proxy不一样的是，不管是接口还是类他都能正常工作。
        Enhancer enhancer = new Enhancer();
        //2.为其设置父类
        enhancer.setSuperclass(userDao.getClass());
        //3.设置回调
        enhancer.setCallback(this);
        //4.创建代理
        UserDao userDaoProxy = (UserDao) enhancer.create();
        return userDaoProxy;
    }


    @Override
    public Object intercept(Object proxy, Method method, Object[] args,
                            MethodProxy methodProxy) throws Throwable {

        if ("insert".equals((method.getName())) || "delete".equals(method.getName())) {
            //记录日志
            System.out.println("日志打印");
            Object obj = methodProxy.invokeSuper(proxy, args);
            return obj;
        }
        return methodProxy.invokeSuper(proxy, args);
    }
}
