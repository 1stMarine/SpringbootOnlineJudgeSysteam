package com.ckw.judger.pojo;

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
    private int qid;
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
