package com.ckw.common.config;

import com.ckw.common.filter.AuthHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 凯威
 * 配置要拦截的路径
 */
@Configuration
public class AuthWebMvcConfigurer implements WebMvcConfigurer
{
    @Autowired
    AuthHandlerInterceptor authHandlerInterceptor;

    /**
     * 给除了 /login 的接口都配置拦截器,拦截转向到 authHandlerInterceptor
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authHandlerInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login")
                .excludePathPatterns("/registerUser")
                .excludePathPatterns("/getQuestionList/{page}")
                .excludePathPatterns("/getSubmitRecordsWithQid/{qid}/{page}")
                .excludePathPatterns("/getQuestion/{qid}")
                .excludePathPatterns("/getMatchList")
                .excludePathPatterns("/getMatchDetail/{mid}")
        ;
    }

}
