package com.ckw.question.server.impl;

import com.ckw.question.mapper.RecordMapper;
import com.ckw.question.pojo.UserResolve;
import com.ckw.question.pojo.dto.UserResolveDto;
import com.ckw.question.server.UserQuestionService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserQuestionServiceImpl implements UserQuestionService {

    @Autowired
    private RecordMapper recordMapper;

    /**
     * @param uid
     * @return
     */
    @Override
    public List<UserResolveDto> getUserResolve(int uid) {
        UserResolve perDifficultySolve = recordMapper.getPerDifficultySolve(uid);

        List<UserResolveDto> objects = new ArrayList<>();
        objects.add(new UserResolveDto(perDifficultySolve.getEasyResolve(), "简单"));
        objects.add(new UserResolveDto(perDifficultySolve.getMeddleResolve(), "中等"));
        objects.add(new UserResolveDto(perDifficultySolve.getHardResolve(), "困难"));
        objects.add(new UserResolveDto(perDifficultySolve.getNighmareRresolve(), "噩梦"));
        return objects;
    }

    /**
     * @param uid
     * @return
     */
    @Override
    public List<UserResolveDto> getUserResolveWithPercent(int uid) {
        UserResolve perDifficultySolve = recordMapper.getPerDifficultySolve(uid);

        int easy = recordMapper.countPerDifficultyCount("简单");
        int meddle = recordMapper.countPerDifficultyCount("中等");
        int hard = recordMapper.countPerDifficultyCount("困难");
        int nightmare = recordMapper.countPerDifficultyCount("噩梦");
        System.out.println("=============================================================== " + easy + " --- --- ---" + perDifficultySolve.getEasyResolve() / easy);

        List<UserResolveDto> objects = new ArrayList<>();
        objects.add(new UserResolveDto((double) perDifficultySolve.getEasyResolve() / easy * 100, "简单"));
        objects.add(new UserResolveDto((double) perDifficultySolve.getMeddleResolve() / meddle * 100, "中等"));
        objects.add(new UserResolveDto((double) perDifficultySolve.getHardResolve() / hard * 100, "困难"));
        objects.add(new UserResolveDto((double) perDifficultySolve.getNighmareRresolve() / nightmare * 100, "噩梦"));
        return objects;
    }
}
