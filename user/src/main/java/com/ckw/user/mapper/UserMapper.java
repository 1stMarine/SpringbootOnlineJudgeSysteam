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

    /**
     * 得到用户列表根据经验值排序
     * @return
     */
    List<User> getUserListForSort();

    List<User> getUserList(Integer page);

    User getUserById(long id);
    boolean changeUserResolve(String difficulty,long id);

    boolean addExperience(int experience,long id);

    int getExperience(long uid);

    int getLevel(long uid);

    boolean addLevel(long uid);

    boolean changeUserInfo(User user);

    /**
     * 增加用户粉丝数
     * @param uid
     * @param num
     * @return
     */
    boolean changeUserFansCount(long uid,int num);

    /**
     * 增加用户关注数
     * @param uid
     * @param num
     * @return
     */
    boolean changeUserSubscribeCount(long uid,int num);

    /**
     * 增加关注者和被关注者的联系
     * @param uid
     * @param fanUid
     * @return
     */
    boolean addUserFans(long uid,long fanUid,String time);

    /**
     * 改变关注状态
     * @param uid
     * @param fanUid
     * @param state
     * @return
     */
    boolean changeSubscribeState(long uid,long fanUid,int state);

    Integer querySubscribeState(long uid,long fanUid);

    /**
     * 更改用户封禁状态
     * @param uid
     * @param ban
     * @return
     */
    boolean updateUserBanState(String uid,Integer ban);

    Integer userCount();

}
