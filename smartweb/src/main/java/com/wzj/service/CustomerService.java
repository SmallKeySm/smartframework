package com.wzj.service;

import com.wzj.annotation.Service;
import com.wzj.cache.Cache;

@Service
public class CustomerService {

    @Cache
    public void sayGood(){
        System.out.println("欢迎光临");
    }
}
