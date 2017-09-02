package com.wzj.service;

import com.wzj.annotation.Service;
import com.wzj.cache.Cache;

@Service
public class SystemService {

    @Cache
    public void printLog(){
        System.out.println("这是系统日志类");
    }
}
