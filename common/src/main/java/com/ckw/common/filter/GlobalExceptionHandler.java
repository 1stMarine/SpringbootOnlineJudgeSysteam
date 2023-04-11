package com.ckw.common.filter;

import com.ckw.common.exception.TokenAuthExpireException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 用户 token 过期
     * @return
     */
    @ExceptionHandler(value = TokenAuthExpireException.class)
    @ResponseBody
    public String tokenExpiredExceptionHandler(){
        log.warn("用户 token 过期");
        return "用户 token 过期";
    }
}
