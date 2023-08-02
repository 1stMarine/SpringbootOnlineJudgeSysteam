package com.ckw.question.server;

import com.ckw.question.pojo.Question;
import com.ckw.question.pojo.TestSamples;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 凯威
 */
public interface Questionserver {

    /**
     * 根据id拿题目
     * @param id 题目id
     * @return 题目对象
     */
    Question queryQuestion(long id);
    /**
     * 得到所有题目
     * @return 题目对象集合
     */
    List<Question> queryQuestionList(int page,long uid);


    List<List<Question>> getQuestionSelect();
    /**
     * 文件上传
     * @param file
     * @return
     */
    String uploadQuestionXml(MultipartFile file);

    /**
     *
     * @param page
     * @param search
     * @return
     */
    List<Question> querySearchQuestionList(int page,String search);

    int countQuestion();

    /**
     * 得到用户解决过的题目
     * @param uid 用户id
     * @return 已题目列表
     */
    List<Long> getUserResolveQuestionId(long uid);

    TestSamples getQuestionTestSample(String qid);

    /**
     * 更改题目信息
     * @param question
     * @return
     */
    boolean changeQuestionInfo(Question question);

    /**
     * 删除题目
     * @param qid
     * @return
     */
    boolean deleteQuestion(String qid);

    /**
     * 添加题目
     * @param question
     * @return
     */
    boolean addQuestion(Question question);
}
