package com.wzj.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class ClassUtil {

    private static final Logger logger = LoggerFactory.getLogger(ClassUtil.class);

    /**
     * 获取类加载器
     *
     * @return
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 加载类
     *
     * @param className  类名
     * @param initialize 是否初始化
     * @return
     */
    public static Class<?> loadClass(String className, boolean initialize) {
        Class<?> aClass = null;
        try {
            aClass = Class.forName(className, initialize, getClassLoader());
        } catch (ClassNotFoundException e) {
            logger.error("加载类出错", e);
        }
        return aClass;
    }

    /**
     * 获取包下的所有类
     *
     * @param packageName
     * @return
     */
    public static Set<Class<?>> getClassSet(String packageName) {
        Set<Class<?>> classSet = new HashSet<>();

        return classSet;
    }
}
