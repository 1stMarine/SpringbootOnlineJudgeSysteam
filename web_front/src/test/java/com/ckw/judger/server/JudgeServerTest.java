package com.ckw.judger.server;

import com.ckw.judger.pojo.SubmitRecord;
import com.ckw.question.mapper.QuestionMapper;
import com.ckw.question.pojo.MatchResult;
import com.ckw.question.server.impl.WekaServiceImpl;
import com.ckw.question.mapper.RecordMapper;
import com.ckw.web_front.WebFrontApplication;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = WebFrontApplication.class)
public class JudgeServerTest{

    @Autowired
    private WekaServiceImpl wekaService;


    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private RecordMapper recordMapper;
    @Test
    public void wekaTest() {
        List<SubmitRecord> allSubmitRecordList = recordMapper.getAllSubmitRecordList();
        List<String> codes = new ArrayList<>();
        for (SubmitRecord submitRecord : allSubmitRecordList) {
            codes.add(submitRecord.getCode());
        }
        int bestK = 1;
        try {
            bestK = wekaService.findBestK(codes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        bestK--;
        Map<Integer, List<SubmitRecord>> cluster = wekaService.cluster(codes, allSubmitRecordList, bestK);
        for (Integer integer : cluster.keySet()) {
            System.out.println("聚类 " + integer);
            List<SubmitRecord> submitRecords = cluster.get(integer);
            for (SubmitRecord submitRecord : submitRecords) {
                System.out.println(submitRecord.getId());
            }
        }
    }

    @Test
    public void matchTest() throws JSONException {

//        List<String> matchQuestionIds = questionMapper.getMatchQuestionIds(288273664);
//        JSONObject jsonObject = new JSONObject();
//        for (String matchQuestionId : matchQuestionIds) {
//            jsonObject.put(matchQuestionId,"Empty");
//        }
//        MatchResult matchResult = new MatchResult(SnowflakeIdWorker.nextId(),11,288273664, jsonObject.toString(),0 );
//        System.out.println(matchResult);
//        recordMapper.addUserMatchResult(matchResult);
        MatchResult userMatchResult = recordMapper.getUserMatchResult(288273664, 11);
        JSONObject jsonObject = new JSONObject(userMatchResult.getResults());
        jsonObject.put(String.valueOf(281961728),"Accept");
        userMatchResult.setResults(jsonObject.toString());
        recordMapper.updateUserMatchResult( userMatchResult);
        System.out.println(userMatchResult);
    }

}
