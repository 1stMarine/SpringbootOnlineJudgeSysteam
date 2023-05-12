package com.ckw.user.mapper;

import com.ckw.user.pojo.User;
import com.ckw.user.pojo.UserDaySubmit;
import com.ckw.user.pojo.UserMonthSubmit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRankMapper {

    void updateRank(List<User> users,int year,int month);

    /**
     * 得到所有用户的数量
     * @return
     */
    int getTotalUser();

    /**
     * 得到用户某一年的解题数量
     * @param uid
     * @return
     */
    List<UserMonthSubmit> getPerMonthUserSubmit(int uid, int year);

    List<UserDaySubmit> getPerDayUserSubmit(int uid,String year);


    int checkDaySubmit(String date,int uid);

    boolean insertDaySubmit(String date,int uid);

    boolean addDayUserSubmit(String date,int uid);


    int checkMonthSubmit(int year,int month,int uid);

    boolean insertMonthSubmit(int year,int month,int uid);
    boolean addMonthUserSubmit(int year,int month,int uid);
}
