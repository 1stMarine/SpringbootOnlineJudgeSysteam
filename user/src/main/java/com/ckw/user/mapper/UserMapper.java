package com.ckw.user.mapper;

import com.ckw.user.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    boolean addUser(User user);

    int existUser(String email);

    User getUser(String email);

    List<User> getUserListForSort();

    User getUserById(int id);
    boolean changeUserResolve(String difficulty,int id);

    boolean addExperience(int experience,int id);

    int getExperience(int uid);

    int getLevel(int uid);

    boolean addLevel(int uid);

    boolean changeUserInfo(User user);


}
