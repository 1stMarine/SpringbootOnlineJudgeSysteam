package com.ckw.match.pojo;

import com.ckw.judger.pojo.TestResult;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class UserMatchingInfo implements Serializable {
    /**
     * 缓存key userId + matchId
     */
    private String umid;
    /**
     * 一共有几个题目
     */
    private List<String> qids;
    /**
     * key:题目id
     * value:测试结果集合
     */
    private Map<String,List<TestResult>> testResultMap = new HashMap<>();;


    public UserMatchingInfo(String umid, List<String> qids) {
        this.umid = umid;
        this.qids = qids;
        for (String qid : qids) {
            testResultMap.put(qid,new ArrayList<>());
        }
    }
}
