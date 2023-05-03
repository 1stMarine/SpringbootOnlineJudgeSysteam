package com.ckw.question.server;

import com.ckw.question.pojo.Question;
import com.ckw.question.server.impl.QuestionServerImpl;
import com.ckw.web_front.WebFrontApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = WebFrontApplication.class)
public class QuestionServerTest {

    @Autowired
    private QuestionServerImpl questionServer;

    @Test
    public void queryQuestion(){
        Question question = questionServer.queryQuestion(1);
        System.out.println(question);
    }
}
