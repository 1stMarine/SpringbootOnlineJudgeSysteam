package com.ckw.user.controller;

import com.ckw.common.pojo.Message;
import com.ckw.common.pojo.State;
import com.ckw.user.server.Impl.UserSubmitInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminUserController {

    @Autowired
    private UserSubmitInfoServiceImpl userRankInfoService;


}
