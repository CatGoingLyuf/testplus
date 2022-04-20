package com.example.JDKProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author lyuf
 * @Date 2021/7/19 13:43
 * @Version 1.0
 * 实现InvocationHandler接口
 * 代理执行的地方
 * 可以自定义业务逻辑(比如日志记录, 权限拦截, 统计等)
 */
public class JDKProxy implements InvocationHandler {
    private UserDao userDao;

    public JDKProxy(UserDao userDao) {
        this.userDao = userDao;
    }

    // 调用目标对象的任何一个方法 都相当于调用invoke();
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        if ("insert".equals((method.getName())) || "delete".equals(method.getName())) {
            //记录日志
            System.out.println("日志打印");
            Object invoke = method.invoke(userDao, args);
            return invoke;
        }
        return method.invoke(userDao,args);
    }
}
