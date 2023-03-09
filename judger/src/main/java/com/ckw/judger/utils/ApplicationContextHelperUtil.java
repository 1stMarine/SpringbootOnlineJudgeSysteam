package com.ckw.judger.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author yichuan@iscas.ac.cn
 * 此工具类用于从Spring的上下文中去获取到类，解决@autowird注入空指针的问题
 * @version 1.0
 * @date 2020/10/27 16:54
 */
@Component
public class ApplicationContextHelperUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext( ApplicationContext applicationContext1 ) throws BeansException {
        applicationContext = applicationContext1;
    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> clazz) {
        return (T) applicationContext.getBean(clazz);
    }
}

