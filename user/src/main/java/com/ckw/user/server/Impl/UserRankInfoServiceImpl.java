package com.ckw.user.server.Impl;

import com.ckw.user.mapper.UserMapper;
import com.ckw.user.pojo.User;
import com.ckw.user.server.UserRankInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
@Service
public class UserRankInfoServiceImpl implements UserRankInfoService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void sortUser() {
        List<User> userList = userMapper.getUserListForSort();
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
