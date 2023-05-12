package com.ckw.user.pojo.dto;

import com.ckw.common.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor

public class UserMonthSubmitDto {
    private List<String> monthsName = new ArrayList<>();

    private List<Integer> monthsData = new ArrayList<>();

    public UserMonthSubmitDto() {
        int month = DateUtils.getMonth();
        for (int i = 1; i <= month; i++) {
            monthsName.add(i+"月");
            monthsData.add(0);
        }
    }
}
