package com.ckw.user.pojo.dto;

import com.ckw.common.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor

public class UserDaySubmitDto {
    private Map<String,Integer> daySubmitMap = new HashMap<>();
    public UserDaySubmitDto() {

        int year = DateUtils.getYear();
        int month = DateUtils.getMonth();
        for (int i = 1; i <= month ; i++) {
            int monthDays = DateUtils.getMonthDays(year, i);
            if(month == i){
                monthDays = DateUtils.getDay();
            }
            for (int j = 1; j <= monthDays; j++) {
                daySubmitMap.put((year+"-"+i+"-"+j),0);
            }
        }
    }
}
