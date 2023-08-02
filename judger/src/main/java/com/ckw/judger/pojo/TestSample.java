package com.ckw.judger.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 凯威
 * 测试样例对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestSample {
    /**
     * 题目id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private long qid;
    /**
     * 标准输入
     */
    private String input;
    /**
     * 标准输出
     */
    private String output;
    /**
     * 用户的输出
     */
    private String userOutput;
    /**
     * 是否正确
     */
    private boolean right;

}
