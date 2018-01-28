package com.leo.springcloud.dao;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@CacheConfig(cacheNames = "users:test")
public class DummyDao {


    public Integer find(Integer i){
        return Integer.valueOf(new Random().nextInt());
    }
}
