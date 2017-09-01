package com.wzj.aspect;

import com.wzj.annotation.Aspect;
import com.wzj.annotation.Service;
import com.wzj.proxy.AspectProxy;

import java.lang.reflect.Method;

@Aspect(Service.class)
public class ControllerAspect extends AspectProxy {

    @Override
    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
        System.out.println("开始访问");
    }

    @Override
    public void after(Class<?> cls, Method method, Object[] params) throws Throwable {
        System.out.println("访问结束");
    }
}
