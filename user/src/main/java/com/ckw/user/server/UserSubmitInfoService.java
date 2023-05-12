package com.ckw.user.server;

import com.ckw.user.pojo.UserMonthSubmit;
import com.ckw.user.pojo.dto.UserMonthSubmitDto;

import java.util.List;

public interface UserSubmitInfoService {

    UserMonthSubmitDto getMonthSubmit(int uid );

    List<List<Object>> getDaySubmit (int uid);


}
