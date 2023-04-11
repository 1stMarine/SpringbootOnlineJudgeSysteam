package com.ckw.user.server;

import com.ckw.user.pojo.User;

public interface UserServer {

    public boolean registerUser(User user);

    public User login(User user);

    boolean changeUserInfo(User user);

    User loadUserInfo(int id);
}
