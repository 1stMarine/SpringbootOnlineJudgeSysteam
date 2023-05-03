package com.ckw.user.controller;

import com.ckw.common.pojo.Message;
import com.ckw.common.pojo.State;
import com.ckw.user.server.Impl.UserRankInfoServiceImpl;
import com.ckw.user.server.UserRankInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminUserController {

    @Autowired
    private UserRankInfoServiceImpl userRankInfoService;

    @PostMapping("/sortUserRank")
    public Object sortUserRank(){
        userRankInfoService.sortUser();
        return new Message(State.SUCCESS,true,"操作成功!");
    }
}
