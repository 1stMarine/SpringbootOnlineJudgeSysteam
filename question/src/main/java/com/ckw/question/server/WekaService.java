package com.ckw.question.server;

import com.ckw.judger.pojo.SubmitRecord;

import java.util.List;
import java.util.Map;

public interface WekaService {

    public Map<Integer, List<SubmitRecord>> cluster(List<String> texts, List<SubmitRecord> records, int k);

    public int findBestK(List<String> texts) throws Exception;

    Map<Integer, List<SubmitRecord>> calculate(long mid);

}
