package com.ckw.user.server;

import com.ckw.user.pojo.UserMonthSubmit;
import com.ckw.user.pojo.dto.UserMonthSubmitDto;

import java.util.List;

public interface UserSubmitInfoService {

    UserMonthSubmitDto getMonthSubmit(long uid );

    List<List<Object>> getDaySubmit (long uid);


}
