package com.ckw.judger.pojo;

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
    private int id;
    private int uid;
    private int qid;
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
