package com.ckw.question.exception;

import com.ckw.common.pojo.Message;
import com.ckw.common.pojo.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionAdvice {
    public static Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);



    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Message handleException(RuntimeException e) {
        logger.error("异常信息：", e.getMessage());
        Message result = new Message();
        result.setState(State.FAILURE);
        result.setMessage(e.getMessage());
        return result;
    }



}