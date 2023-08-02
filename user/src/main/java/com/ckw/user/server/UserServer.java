package com.ckw.user.server;

import com.ckw.common.pojo.Message;
import com.ckw.user.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserServer {

    public Message registerUser(User user);

    public User login(User user);

    boolean changeUserInfo(User user);

    User loadUserInfo(long id);

    boolean subscribeUser(long uid,long fansUid);

    boolean unSubscribeUser(long uid,long fansUid);

    Map<String,Object> getUserList(Integer page);

    Boolean banUser(String uid,Integer ban);
}
