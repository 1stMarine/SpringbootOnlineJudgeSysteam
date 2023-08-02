package com.ckw.common.controller;


import com.ckw.common.pojo.Message;
import com.ckw.common.pojo.State;
import com.ckw.common.utils.SystemInfoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;


@RestController
@Slf4j
public class SystemInfoController {
    @GetMapping("/getSysInfo")
    public Object getSysInfo() throws UnknownHostException {
        return new Message(State.SUCCESS, SystemInfoUtils.getInfo(),"获取成功!");
    }



}
