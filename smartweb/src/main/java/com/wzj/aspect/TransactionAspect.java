package com.wzj.aspect;

import com.wzj.annotation.Aspect;
import com.wzj.annotation.Service;
import com.wzj.proxy.AspectProxy;

import java.lang.reflect.Method;

@Aspect(value = Service.class)
public class TransactionAspect extends AspectProxy{
    @Override
    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
        System.out.println("事务开始");
    }

    @Override
    public void after(Class<?> cls, Method method, Object[] params) throws Throwable {
        System.out.println("事务结束");
    }
}
