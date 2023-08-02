package com.ckw.judger.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
    @JsonSerialize(using = ToStringSerializer.class)
    private long uid;

    private String questionName;
    @JsonSerialize(using = ToStringSerializer.class)
    private long qid;

    private Double time;

    private Double memory;

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

    private String message;
    private String title;
}
