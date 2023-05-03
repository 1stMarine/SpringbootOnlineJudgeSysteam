package com.ckw.match.pojo;

import com.ckw.question.pojo.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Match {

    private int id;
    private String matchName;
    private String matchDescription;
    private String createTime;
    private String startTime;
    private String endTime;
    private int persistentTime;
    private int participationCount;
    private String matchType;
    private String imgUrl;
    private String state;
    private List<String> questionIds;

    private List<Question> questions;
}
