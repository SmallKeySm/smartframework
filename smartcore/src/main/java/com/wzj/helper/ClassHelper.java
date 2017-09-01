package com.wzj.helper;

import com.wzj.annotation.Controller;
import com.wzj.annotation.Service;
import com.wzj.util.ClassUtil;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

public final class ClassHelper {

    private static Set<Class<?>> CLASS_SET = null;

    static {
        String basePackagePath = ConfigHelper.getAppBasePath();
        CLASS_SET = ClassUtil.getClassSet(basePackagePath);
    }

    /**
     * 获取所有类
     * @return
     */
    public static Set<Class<?>> getClassSet(){
        return CLASS_SET;
    }

    /**
     * 根据指定注解获取所有类
     * @param aClass
     * @return
     */
    public static Set<Class<?>> getClassSet(Class<? extends Annotation> aClass){
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> aClass1 : classSet){
            if (aClass1.isAnnotationPresent(aClass)){
                classSet.add(aClass1);
            }
        }
        return classSet;
    }

    /**
     * 获取所有bean class 集合
     * @return
     */
    public static Set<Class<?>> getBeanClassSet(){
        Set<Class<?>> classSet = new HashSet<>();
        classSet.addAll(getClassSet(Controller.class));
        classSet.addAll(getClassSet(Service.class));
        return classSet;
    }

}
