package com.wzj.cache;

import com.wzj.proxy.Proxy;
import com.wzj.proxy.ProxyChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * 缓存代理
 */
public class CacheProxy implements Proxy {

    private static final Logger logger = LoggerFactory.getLogger(CacheProxy.class);

    @Override
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result = null;
        Method method = proxyChain.getTargetMethod();
        result = proxyChain.doProxyChain();
        if (method.isAnnotationPresent(Cache.class)){
            // TODO: 2017/9/2 结果集加入缓存
            System.out.println("已加入缓存");
        } 
        return result;
    }
}
