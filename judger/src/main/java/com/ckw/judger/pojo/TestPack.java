package com.ckw.judger.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 凯威
 * 判题内容
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestPack {
    /**
     * 必须 : 用户id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private long uid;
    private String userName;
    /**
     * 必须 : 题目id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private long qid;
    /**
     * 题目名称
     */
    private String questionName;
    /**
     * 必须 : 编程语言
     */
    private String language;
    /**
     * 必须 : 提交的代码
     */
    private String code;
    /**
     * 必须 : 类型 -> 普通、运行测试、比赛
     */
    private Integer type;
    /**
     * 比赛id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private long mid;
    /**
     * 测试样例对象集合
     */
    private List<TestSample> testSampleList = new ArrayList<>();
    /**
     * 提交时间
     */
    private String submitTimeFormat;
    /**
     * 提交时间戳
     */
    private long submitTime;
    /**
     * 创建命令
     */
    private String createCommand;
    /**
     * 时间限制
     */
    private double timeLimit;
    /**
     * 内存限制
     */
    private int memoryLimit;


    /**
     * 容器相关 编译、执行、停止、删除
     */
    private String compileCommand;
    private String executeCommand;
    private String stopCommand;
    private String deleteCommand;
    /***
     * 容器id
     */
    private String containerId;
    /**
     * 编译别名
     */
    private String name;
}
