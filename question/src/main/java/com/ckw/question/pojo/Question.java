package com.ckw.question.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 凯威
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    /**
     * 题目id
     */
    private Integer id;
    /**
     * 题目名
     */
    private String questionName;
    /**
     * 输入格式说明
     */
    private String inputStyle;
    /**
     * 输出格式说明
     */
    private String outputStyle;
    /**
     * 例题 （json数组）
     */
    private String inputSample;
    /**
     * 例题解 （json数组）
     */
    private String outputSample;
    /**
     * 数据范围说明
     */
    private String dataRange;
    /**
     * 难度
     */
    private String difficulty;
    /**
     * 时间限制
     */
    private int timeLimit;
    /**
     * 空间限制
     */
    private int memoryLimit;
    /**
     * 题目描述
     */
    private String description;
    /**
     * 总通过
     */
    private int totalPass;
    /**
     * 总尝试
     */
    private int totalAttempt;
    /**
     * 问题来源
     */
    private String resource;
    /**
     * 问题标签 （json数组）
     */
    private String tag;
    /**
     * 测试用力对象合集 （上传题目用）
     */
    private TestSamples testSamples;
    /**
     * 通过率
     */
    private double passRate;

    private int privateState = 0;
    /**
     * 用户是否通过
     */
    private int uid;
}
