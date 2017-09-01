package com.wzj.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 反射工具类
 */
public class ReflectionUtil {

    private static final Logger logger = LoggerFactory.getLogger(ReflectionUtil.class);

    public static Object newInstance(Class<?> aClass) {
        Object obj = null;
        try {
            obj = aClass.newInstance();
        } catch (Exception e) {
            logger.error("实例化出错", e);
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static Object invokeMethod(Object obj, Method method, Object... args) {
        Object returnValue = null;
        if (!Modifier.isPublic(method.getModifiers())) {
            method.setAccessible(true);
        }
        try {
            returnValue = method.invoke(obj, args);
        } catch (Exception e) {
            logger.error("方法调用出错", e);
            throw new RuntimeException(e);
        }
        return returnValue;
    }

    public static Object setFieldValue(Object obj, Field field, Object value) {
        if (!Modifier.isPublic(field.getModifiers())) {
            field.setAccessible(true);
        }
        try {
            field.set(obj, value);
        } catch (Exception e) {
            logger.error("设置值出错");
            throw new RuntimeException(e);
        }
        return obj;
    }
}
