package com.ckw.user.controller;

import com.ckw.common.pojo.Message;
import com.ckw.common.pojo.State;
import com.ckw.common.pojo.ToEmail;
import com.ckw.common.utils.EmailUtils;
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
    private EmailUtils emailUtils;

    @Autowired
    private UserSubmitInfoServiceImpl userSubmitInfoService;
    @PostMapping("/registerUser")
    public Object register(@RequestBody User user){
        return userServer.registerUser(user);
    }

    @PostMapping("/login")
    public Object login(@RequestBody User user){
        User login = userServer.login(user);
        if(login.getId() == 0){
            return new Message(State.FAILURE,null,"登陆失败!");
        }
        if(login.getBan() > 0){
            return new Message(State.BAN,null,"此账号已被限制登录!");
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
    public Object loadUserInfo(@PathVariable long uid){
        return new Message(State.SUCCESS,userServer.loadUserInfo(uid),"操作成功!");
    }


    @GetMapping("/getUserMonthSubmit/{uid}")
    public Object getUserMonthSubmit(@PathVariable long uid){
        return new Message(State.SUCCESS,userSubmitInfoService.getMonthSubmit(uid),"获取成功！");
    }

    @GetMapping("/getUserDaySubmit/{uid}")
    public Object getUserDaySubmit(@PathVariable long uid){
        return new Message(State.SUCCESS,userSubmitInfoService.getDaySubmit(uid),"获取成功！");
    }

    @GetMapping("/subscribe/{uid}/{fanUid}")
    public Object subScribeUser(@PathVariable long uid,@PathVariable long fanUid){
        return new Message(State.SUCCESS,userServer.subscribeUser(uid,fanUid),"关注成功!");
    }

    @GetMapping("/unSubscribe/{uid}/{fanUid}")
    public Object unSubscribe(@PathVariable long uid,@PathVariable long fanUid){
        return new Message(State.SUCCESS,userServer.unSubscribeUser(uid,fanUid),"取消关注成功!");
    }

    @PostMapping("/sendVerCode")
    public Object sendVerCode(@RequestParam("email") String email){
        String[] emails = new String[]{email};
        ToEmail toEmail = new ToEmail(emails, "", "");
        emailUtils.sendVerCode(toEmail);
        return new Message(State.SUCCESS,true,"发送成功!");
    }

    @GetMapping("/getUserList/{page}")
    public Object getUserList(@PathVariable Integer page){
        return new Message(State.SUCCESS,userServer.getUserList(page),"获取成功!");
    }

    @PutMapping ("/banUser")
    public Object banUser(@RequestParam("uid") String uid,@RequestParam("ban") Integer ban){
        Boolean bandDone = userServer.banUser(uid,ban);
        return new Message(bandDone ? State.SUCCESS : State.FAILURE,bandDone,bandDone?"操作成功!" : "操作失败!");

    }
}
