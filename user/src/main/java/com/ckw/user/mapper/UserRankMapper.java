package com.ckw.user.mapper;

import com.ckw.user.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRankMapper {

    void updateRank(List<User> users,int year,int month);

}
