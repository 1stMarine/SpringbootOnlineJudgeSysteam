package com.ckw.user.controller;

import com.ckw.common.pojo.Message;
import com.ckw.common.pojo.State;
import com.ckw.common.utils.TokenUtil;
import com.ckw.user.pojo.User;
import com.ckw.user.server.Impl.UserServerImpl;
import com.ckw.user.server.Impl.UserSubmitInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    private UserServerImpl userServer;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private UserSubmitInfoServiceImpl userSubmitInfoService;
    @PostMapping("/registerUser")
    public Object register(@RequestBody User user){

        return new Message(State.SUCCESS,userServer.registerUser(user),"提交成功!");
    }

    @PostMapping("/login")
    public Object login(@RequestBody User user){
        User login = userServer.login(user);
        if(login.getId() == 0){
            return new Message(State.FAILURE,null,"登陆失败!");
        }
        String token = tokenUtil.getToken(user.getEmail(),login.getRole());
        login.setToken(token);
        return new Message(State.SUCCESS,login,"登陆成功!");
    }

    @PostMapping("/getToken")
    public Object getToken(HttpServletRequest request){
        String token = request.getHeader("token");

        return tokenUtil.parseToken(token);
    }

    @PostMapping("/changeUserInfo")
    public Object changeUserInfo(@RequestBody User user){

        return new Message(State.SUCCESS,userServer.changeUserInfo(user),"操作成功!");
    }

    @GetMapping("/loadUserInfo/{uid}")
    public Object loadUserInfo(@PathVariable int uid){
        return new Message(State.SUCCESS,userServer.loadUserInfo(uid),"操作成功!");
    }


    @GetMapping("/getUserMonthSubmit/{uid}")
    public Object getUserMonthSubmit(@PathVariable int uid){
        return new Message(State.SUCCESS,userSubmitInfoService.getMonthSubmit(uid),"获取成功！");
    }

    @GetMapping("/getUserDaySubmit/{uid}")
    public Object getUserDaySubmit(@PathVariable int uid){
        return new Message(State.SUCCESS,userSubmitInfoService.getDaySubmit(uid),"获取成功！");
    }
}
