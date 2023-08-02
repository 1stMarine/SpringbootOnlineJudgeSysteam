package com.ckw.question.server;

import com.ckw.question.pojo.UserResolve;
import com.ckw.question.pojo.dto.UserResolveDto;
import org.json.JSONArray;

import java.util.List;

public interface UserQuestionService {

    List<UserResolveDto> getUserResolve(long uid);

    List<UserResolveDto> getUserResolveWithPercent(long uid);
}
