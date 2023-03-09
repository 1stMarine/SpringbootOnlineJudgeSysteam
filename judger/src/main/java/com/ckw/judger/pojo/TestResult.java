package com.ckw.judger.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 凯威
 * 测试结果
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestResult {

    private String uid;

    private String questionName;

    private String qid;

    private Double time;

    private int memory;

    private boolean isPass;

    private String submitTimeFormat;

    private String code;
    /**
     * 如果答案错误，存入错误的答案
     */
    private TestSample testSample;

    /**
     * 编译或者运行报错放这里
     */
    private String wrongMessage;
}
