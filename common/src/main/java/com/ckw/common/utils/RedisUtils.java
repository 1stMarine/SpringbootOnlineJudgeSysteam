package com.ckw.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {


    @Autowired
    private RedisTemplate redisTemplate;

    public void insertObject(String key,Object value){
        redisTemplate.opsForValue().set(key,value);
    }

    /**
     * 设置过期时间 ，单位：秒
     * @param key
     * @param value
     * @param time
     */
    public void insertObject(String key,Object value,long time){
        redisTemplate.opsForValue().set(key,value,time, TimeUnit.SECONDS);
    }


    public Object getObject(String key){
        return redisTemplate.opsForValue().get(key);
    }


}
