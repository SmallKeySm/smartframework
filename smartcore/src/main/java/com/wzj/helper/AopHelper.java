package com.wzj.helper;

import com.wzj.annotation.Aspect;
import com.wzj.annotation.Service;
import com.wzj.cache.CacheProxy;
import com.wzj.proxy.AspectProxy;
import com.wzj.proxy.Proxy;
import com.wzj.proxy.ProxyManager;
import com.wzj.transaction.TransactionProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.*;
/**
 * aop 工具类
 */
public class AopHelper {

    private static final Logger logger = LoggerFactory.getLogger(AopHelper.class);

    static {
        try {
            Map<Class<?>, Set<Class<?>>> proxyMap = createProxyMap();
            Map<Class<?>, List<Proxy>> targetMap = createTargetMap(proxyMap);
            for (Map.Entry<Class<?>, List<Proxy>> targetEntry : targetMap.entrySet()){
                Class<?> targetClass = targetEntry.getKey();
                List<Proxy> proxyList = targetEntry.getValue();
                Object object = ProxyManager.createProxy(targetClass, proxyList);
                BeanHelper.objectMap.put(targetClass, object);
            }
        } catch (Exception e) {
            logger.error("aop初始化失败");
        }
    }

    /**
     * 获取指定的所有代理类
     * @param aspect
     * @return
     * @throws Exception
     */
    public static Set<Class<?>> createTargetClassSet(Aspect aspect) throws Exception{
        Set<Class<?>> classSet = new HashSet<>();
        Class<? extends Annotation> annotation = aspect.value();
        if (annotation != null && !annotation.equals(Aspect.class)){
            classSet.addAll(ClassHelper.getClassSet(annotation));
        }
        return classSet;
    }

    /**
     * 添加普通代理类
     * @param proxyMap
     * @throws Exception
     */
    private static void addAspectProxy(Map<Class<?>, Set<Class<?>>> proxyMap) throws Exception{
        Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(AspectProxy.class);
        for (Class<?> proxyClass : proxyClassSet){
            if (proxyClass.isAnnotationPresent(Aspect.class)){
                Aspect aspect = proxyClass.getAnnotation(Aspect.class);
                Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
                proxyMap.put(proxyClass, targetClassSet);
            }
        }
    }

    /**
     * 添加事务代理类
     * @param proxyMap
     */
    private static void addTransactionProxy(Map<Class<?>, Set<Class<?>>> proxyMap){
        Set<Class<?>> serviceClassSet = ClassHelper.getClassSetBySuper(Service.class);
        proxyMap.put(TransactionProxy.class, serviceClassSet);
    }

    /**
     * 添加缓存代理
     * @param proxyMap
     */
    private static void addCacheProxy(Map<Class<?>, Set<Class<?>>> proxyMap){
        Set<Class<?>> serviceClassSet = ClassHelper.getClassSetByAnnoation(Service.class);
        proxyMap.put(CacheProxy.class, serviceClassSet);
    }

    /**
     * 切面类与目标类映射关系
     * @return
     * @throws Exception
     */
    public static Map<Class<?>, Set<Class<?>>> createProxyMap() throws Exception{
        Map<Class<?>, Set<Class<?>>> proxyClassMap = new HashMap<>();
        addAspectProxy(proxyClassMap);
//        addTransactionProxy(proxyClassMap);
        addCacheProxy(proxyClassMap);
        return proxyClassMap;
    }

    public static Map<Class<?>, List<Proxy>> createTargetMap(Map<Class<?>, Set<Class<?>>> proxyMap) throws Exception{
        Map<Class<?>, List<Proxy>> targetMap = new HashMap<>();
        for (Map.Entry<Class<?>, Set<Class<?>>> proxyEntry : proxyMap.entrySet()){
            Class<?> proxyClass = proxyEntry.getKey();
            Set<Class<?>> targetClassSet = proxyEntry.getValue();
            for (Class<?> targetClass : targetClassSet){
                Proxy proxy = (Proxy) proxyClass.newInstance();
                if (targetMap.containsKey(targetClass)){
                    targetMap.get(targetClass).add(proxy);
                } else {
                    List<Proxy> proxies = new ArrayList<>();
                    proxies.add(proxy);
                    targetMap.put(targetClass, proxies);
                }
            }
        }
        return targetMap;
    }
}
