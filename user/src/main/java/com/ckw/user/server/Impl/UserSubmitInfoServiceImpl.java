package com.ckw.user.server.Impl;

import com.ckw.common.utils.DateUtils;
import com.ckw.user.mapper.UserMapper;
import com.ckw.user.mapper.UserRankMapper;
import com.ckw.user.pojo.UserDaySubmit;
import com.ckw.user.pojo.UserMonthSubmit;
import com.ckw.user.pojo.dto.UserDaySubmitDto;
import com.ckw.user.pojo.dto.UserMonthSubmitDto;
import com.ckw.user.server.UserSubmitInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserSubmitInfoServiceImpl implements UserSubmitInfoService {




    @Autowired
    private UserRankMapper userRankMapper;


    /**
     * @param uid
     * @return
     */
    @Override
    public UserMonthSubmitDto getMonthSubmit(long uid) {
//        拿到今年有的数据
        List<UserMonthSubmit> perMonthUserSubmit = userRankMapper.getPerMonthUserSubmit(uid, DateUtils.getYear());
        UserMonthSubmitDto userMonthSubmitDto = new UserMonthSubmitDto();

        for (UserMonthSubmit userMonthSubmit : perMonthUserSubmit) {
            userMonthSubmitDto.getMonthsData().set(userMonthSubmit.getMonth()-1,userMonthSubmit.getCount());
        }


        return userMonthSubmitDto;
    }

    /**
     * @param uid
     */
    @Override
    public List<List<Object>> getDaySubmit(long uid) {
        String year = DateUtils.getYear()+"%";
        List<List<Object>> daySubmit =  new ArrayList<>();
        List<UserDaySubmit> perDayUserSubmit = userRankMapper.getPerDayUserSubmit(uid, year);
        UserDaySubmitDto userDaySubmitDto = new UserDaySubmitDto();
        for (UserDaySubmit userDaySubmit : perDayUserSubmit) {
            userDaySubmitDto.getDaySubmitMap().put(userDaySubmit.getSubmitDate(),userDaySubmit.getSubmit());
        }

        Map<String, Integer> daySubmitMap = userDaySubmitDto.getDaySubmitMap();
        for (String date : daySubmitMap.keySet()) {
            Integer value = daySubmitMap.get(date);
            ArrayList<Object> objects = new ArrayList<>();
            objects.add(date);
            objects.add(value);
            daySubmit.add(objects);
        }

        return daySubmit;
    }
}
