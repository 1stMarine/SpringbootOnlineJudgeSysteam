package com.ckw.question.server;

import com.ckw.question.pojo.Question;
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
    public Question queryQuestion(int id);
    /**
     * 得到所有题目
     * @return 题目对象集合
     */
    public List<Question> queryQuestionList(int page,int uid);


    List<List<Question>> getQuestionSelect();
    /**
     * 文件上传
     * @param file
     * @return
     */
    public String uploadQuestionXml(MultipartFile file);

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
    List<Integer> getUserResolveQuestionId(int uid);
}
