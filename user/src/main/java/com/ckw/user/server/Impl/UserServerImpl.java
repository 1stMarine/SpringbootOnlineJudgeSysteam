package com.ckw.user.server.Impl;

import com.ckw.common.pojo.Message;
import com.ckw.common.pojo.State;
import com.ckw.common.utils.DateUtils;
import com.ckw.common.utils.RedisUtils;
import com.ckw.common.utils.SnowflakeIdWorker;
import com.ckw.common.utils.TokenUtil;
import com.ckw.user.mapper.UserMapper;
import com.ckw.user.pojo.User;
import com.ckw.user.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * @author 凯威
 */
@Service
public class UserServerImpl implements UserServer {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private RedisUtils redisUtils;
    @Override
    public Message registerUser(User user) {
        if(userMapper.existUser(user.getEmail()) == 1){
            return new Message(State.FAILURE,null,"此邮箱已经注册!");
        }
        String verCode = (String)redisUtils.getObject(user.getEmail());
        if(verCode == null){
            return new Message(State.FAILURE,null,"验证邮箱与注册邮箱不一致!");
        }
        if(!verCode.equals(user.getVerCode())){
            return new Message(State.FAILURE,null,"验证码错误!");
        }
//
        user.setId(SnowflakeIdWorker.snowFlow.nextId());
        user.setRole("普通用户");
        user.setUrl("https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png");
        user.setGender("保密");
        user.setPassword(
//                加密密码
                DigestUtils.md5DigestAsHex(user.getPassword().getBytes())
        );

        boolean addUser = userMapper.addUser(user);
        return  new Message(addUser ? State.SUCCESS : State.FAILURE,null,addUser ? "注册成功！" : "注册失败!");
    }

    @Override
    public User login(User user) {
        User loginUser = userMapper.getUser(user.getEmail());

        if(loginUser == null || !DigestUtils.md5DigestAsHex(user.getPassword().getBytes()).equals(loginUser.getPassword())){
            return user;
        }

        return loginUser;
    }

    @Override
    public boolean changeUserInfo(User user) {
        return userMapper.changeUserInfo(user);
    }

    @Override
    public User loadUserInfo(long id) {
        User userById = userMapper.getUserById(id);

        userById.setToken(tokenUtil.getToken(userById.getEmail(),userById.getRole()));
        return userById;
    }

    /**
     * 关注用户
     * @param uid
     * @param fansUid
     * @return
     */
    @Override
    @Transactional
    public boolean subscribeUser(long uid, long fansUid) {
//        先查看有没有关注过的操作
        boolean isSubscribe = userMapper.querySubscribeState(uid,fansUid) > 0;
//        更改用户的关注数、被关注数
        userMapper.changeUserSubscribeCount(fansUid,1);
        userMapper.changeUserFansCount(uid,1);

        if(isSubscribe){
//            有过操作记录直接更改状态
            userMapper.changeSubscribeState(uid,fansUid,1);
        }else{
            userMapper.addUserFans(uid,fansUid, DateUtils.getCurrentTime());

        }
        return true;
    }

    /**
     * 取消关注用户
     * @param uid
     * @param fansUid
     * @return
     */
    @Override
    public boolean unSubscribeUser(long uid, long fansUid) {
//        先查看有没有关注过的操作
        boolean isSubscribe = userMapper.querySubscribeState(uid,fansUid) > 0;
//        更改用户的关注数、被关注数
        userMapper.changeUserSubscribeCount(fansUid,-1);
        userMapper.changeUserFansCount(uid,-1);

        if(isSubscribe){
//            有过操作记录直接更改状态
            userMapper.changeSubscribeState(uid,fansUid,-1);
        }else{
            userMapper.addUserFans(uid,fansUid, DateUtils.getCurrentTime());
        }
        return true;
    }

    /**
     * @return
     */
    @Override
    public Map<String,Object> getUserList(Integer page) {
        Map<String,Object> userMap = new HashMap<String,Object>();
        List<User> userList = userMapper.getUserList(page);
        Integer userCount = userMapper.userCount();
        userMap.put("userList",userList);
        userMap.put("userCount",userCount);
        return userMap;
    }

    /**
     * @param uid
     * @param ban
     * @return
     */
    @Override
    public Boolean banUser(String uid, Integer ban) {
        return userMapper.updateUserBanState(uid,ban);
    }


}
