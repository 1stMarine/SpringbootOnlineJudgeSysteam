package com.ckw.match.pojo;

import com.ckw.question.pojo.Question;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Match {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String matchName;
    private String matchDescription;
    private String createTime;
    private String startTime;
    private String endTime;
    private Integer persistentTime;
    private Integer participationCount;
    private String matchType;
    private String imgUrl;
    private String state;
    private List<String> questionIds;
    private List<Question> questions;
}
