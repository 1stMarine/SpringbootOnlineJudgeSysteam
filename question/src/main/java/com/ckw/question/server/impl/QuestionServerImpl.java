package com.ckw.question.server.impl;

import com.ckw.question.mapper.QuestionMapper;
import com.ckw.question.mapper.RecordMapper;
import com.ckw.question.pojo.Question;
import com.ckw.question.server.Questionserver;
import com.ckw.question.utils.xmlUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 凯威
 */
@Service
@Slf4j
public class QuestionServerImpl implements Questionserver {

    @Autowired
    private RecordMapper recordMapper;

    Path path = Paths.get("question/src/main/resources/question-xml");

    /**
     * 初始化：文件上传临时路径
     */
    @PostConstruct
    public void init(){
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Autowired
    private QuestionMapper questionMapper;


    @Override
    public Question queryQuestion(int id) {
        return questionMapper.queryQuestion(id);
    }


    @Override
    public List<Question> queryQuestionList(int page,int uid) {
        page *= 15;
        return computedPassRate(questionMapper.queryQuestionList(page,uid));
    }



    @Override
    public List<List<Question>> getQuestionSelect() {
        List<List<Question>> questions = new ArrayList<>();
        questions.add(questionMapper.getQuestionSelectEasy());
        questions.add(questionMapper.getQuestionSelectMeddle());
        questions.add(questionMapper.getQuestionSelectHard());
        questions.add(questionMapper.getQuestionSelectNightMare());
        return questions;
    }

    @Override
    public List<Question> querySearchQuestionList(int page, String search) {
        return computedPassRate(questionMapper.searchQuestion(page, search));
    }

    @Override
    public int countQuestion() {
        return questionMapper.countQuestion();
    }

    /**
     * 得到用户解决过的题目
     *
     * @param uid 用户id
     * @return 已题目列表
     */
    @Override
    public List<Integer> getUserResolveQuestionId(int uid) {
        return recordMapper.getUserResolveRecord(uid);
    }

    public List<Question> computedPassRate(List<Question> questions){
        for (Question question : questions) {
            if(question.getTotalAttempt() != 0){
                DecimalFormat df = new DecimalFormat("#.0");
                question.setPassRate(
                        Double.parseDouble(df.format(((question.getTotalPass() * 1.0) / (question.getTotalAttempt() * 1.0))))
                );

            }
        }
        return questions;
    }

    @Override
    @Transactional
    public String uploadQuestionXml(MultipartFile file) {
        try {
//            拷贝文件

            Files.copy(file.getInputStream(), path.resolve(file.getOriginalFilename()));
            boolean flag = false;
            do {
                Thread.sleep(2000);

                flag = new File(path.resolve(file.getOriginalFilename()).toString()).exists();
                System.out.println(flag);

            }while (!flag);



//            解析文件
            Question question = xmlUtils.parseXml(path.resolve(file.getOriginalFilename()).toString());
//            保存题目
            boolean insertQuestion = questionMapper.insertQuestion(question);
            boolean insertTestSamples = questionMapper.insertTestSamples(question);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        finally {
            try {
//                删除临时文件
                Files.delete(path.resolve(file.getOriginalFilename()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }



}
