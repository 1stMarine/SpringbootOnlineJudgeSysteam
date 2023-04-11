package com.ckw.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtils {


    @Autowired
    private RedisTemplate redisTemplate;

    public void insertObject(String key,Object value){
        redisTemplate.opsForValue().set(key,value);
    }


    public Object getObject(String key){
        return redisTemplate.opsForValue().get(key);
    }


}
