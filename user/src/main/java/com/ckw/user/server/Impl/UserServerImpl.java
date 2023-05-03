package com.ckw.user.server.Impl;

import com.ckw.common.utils.SnowflakeIdWorker;
import com.ckw.common.utils.TokenUtil;
import com.ckw.user.mapper.UserMapper;
import com.ckw.user.pojo.User;
import com.ckw.user.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @author 凯威
 */
@Service
public class UserServerImpl implements UserServer {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public boolean registerUser(User user) {
        if(userMapper.existUser(user.getEmail()) == 1){
            return false;
        }
        user.setId(SnowflakeIdWorker.nextId());
        user.setRole("普通用户");
        user.setUrl("https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png");
        user.setGender("保密");
        user.setPassword(
//                加密密码
                DigestUtils.md5DigestAsHex(user.getPassword().getBytes())
        );
        return userMapper.addUser(user);
    }

    @Override
    public User login(User user) {
        User loginUser = userMapper.getUser(user.getEmail());
        System.out.println(loginUser);

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
    public User loadUserInfo(int id) {
        User userById = userMapper.getUserById(id);

        userById.setToken(tokenUtil.getToken(userById.getEmail(),userById.getRole()));
        return userById;
    }


}
