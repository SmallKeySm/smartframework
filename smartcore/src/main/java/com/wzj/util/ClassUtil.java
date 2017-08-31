package com.wzj.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

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
            throw new RuntimeException(e);
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
        ClassLoader classLoader = getClassLoader();
        try {
            Enumeration<URL> urlEnumeration = classLoader.getResources(packageName.replace(".", "/"));
            while (urlEnumeration.hasMoreElements()) {
                URL url = urlEnumeration.nextElement();
                if (url != null) {
                    String protocol = url.getProtocol();
                    if ("file".equals(protocol)) {
                        String packagePath = url.getPath().replace("%20", "");
                        // TODO: 2017/8/31 从包下取class
                    } else if ("jar".equals(protocol)) {
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        if (jarURLConnection != null) {
                            JarFile jarFile = jarURLConnection.getJarFile();
                            Enumeration<JarEntry> jarEntryEnumeration = jarFile.entries();
                            while (jarEntryEnumeration.hasMoreElements()){
                                JarEntry jarEntry = jarEntryEnumeration.nextElement();
                                String jarEntryName = jarEntry.getName();
                                if (jarEntryName.endsWith(".class")){
                                    String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
                                    doLoadClass(classSet, className);
                                } else {

                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            logger.error("加载包出错", e);
            throw new RuntimeException(e);
        }
        return classSet;
    }

    public static void doLoadClass(Set<Class<?>> classSet, String className){
        Class<?> aClass = loadClass(className, false);
        classSet.add(aClass);
    }
}
