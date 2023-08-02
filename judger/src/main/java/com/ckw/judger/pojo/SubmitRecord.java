package com.ckw.judger.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 凯威
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmitRecord {
    @JsonSerialize(using = ToStringSerializer.class)
    private long id;
    @JsonSerialize(using = ToStringSerializer.class)
    private long uid;
    @JsonSerialize(using = ToStringSerializer.class)
    private long qid;
    private String questionName;
    private double time;
    private double memory;
    private String language;
    private String code;
    private String submitTime;
    private String title;
    private String message;
    private String testSample;
    private String userName;
}
