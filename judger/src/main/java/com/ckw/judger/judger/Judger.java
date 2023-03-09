package com.ckw.judger.judger;

import com.ckw.judger.pojo.TestPack;
import com.ckw.judger.pojo.TestResult;

/**
 * @author 凯威
 * 判题辅助,集成判题机核心功能
 */
public class Judger extends JudgeCore{


    public Judger(TestPack testPack) {
        System.out.println(testPack);
        this.testPack = testPack;
    }

    /**
     * 完整的判题机运行过程
     */
    public TestResult run(){

        //  初始化
        this.init();
        //  编译
        String compile = this.compile();
        System.out.println("编译 :" + compile);
        // 编译结果需要为空或null才是编译成功，可以继续执行代码
        if(compile == "" || compile == null){
            String execute = this.execute();
            System.out.println("运行 :" + execute);
        }

        checkAnswer();

        return testResult;

    }
}
