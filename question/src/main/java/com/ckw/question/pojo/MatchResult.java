package com.ckw.question.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchResult {
    private int id;
    private int uid;
    private String userName;
    private int mid;
    private String results;
    private int totalScore;
}
