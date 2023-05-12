package com.ckw.judger.judger;

import com.ckw.common.netty.NioWebSocketHandler;
import com.ckw.judger.pojo.TestPack;
import com.ckw.judger.pojo.TestResult;
import lombok.extern.log4j.Log4j2;

/**
 * @author 凯威
 * 判题辅助,集成判题机核心功能
 */
@Log4j2
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
        NioWebSocketHandler.textWebSocketFrameHandler(
                NioWebSocketHandler.userWebSocketId.get(String.valueOf(testPack.getUid())),
                "2","编译中...","编译完毕"
        );
        String compile = this.compile();
        System.out.println("编译 :" + compile);
        // 编译结果需要为空或null才是编译成功，可以继续执行代码
        if("".equals(compile) || compile == null || " ".equals(compile)){
            log.info("编译成功");
            NioWebSocketHandler.textWebSocketFrameHandler(
                    NioWebSocketHandler.userWebSocketId.get(String.valueOf(testPack.getUid())),
                    "3","运行中...","运行完毕"
            );
            String execute = this.execute();
            System.out.println("运行 :" + execute);
            checkAnswer();

        }else{
            log.info("编译错误");
            testResult.setPass(false);
            testResult.setTitle("Compile Error");
            testResult.setMessage(compile);
        }

        if(testResult.isPass()){
            NioWebSocketHandler.textWebSocketFrameHandler(
                    NioWebSocketHandler.userWebSocketId.get(String.valueOf(testPack.getUid())),
                    "4","返回结果","运行完毕"
            );
        }
        return testResult;

    }
}
