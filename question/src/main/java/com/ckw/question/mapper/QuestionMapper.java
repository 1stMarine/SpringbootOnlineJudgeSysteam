package com.ckw.question.mapper;

import com.ckw.question.pojo.Question;
import com.ckw.question.pojo.TestSamples;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 凯威
 */
@Mapper
@Repository
public interface QuestionMapper {

        /**
         * 根据id查找题目
         * @param id
         * @return 题目对象
         */
        public Question queryQuestion(int id);

        /**
         * 得到所有题目
         * @return
         */
        public List<Question> queryQuestionList(int page);

        /**
         * 插入一道题目
         * @param question 题目对象
         * @return 是否成功
         */
        boolean insertQuestion(Question question);

        /**
         * 插入一个题目的测试样例
         * @param question 题目对象
         * @return 是否成功
         */
        boolean insertTestSamples(Question question);

        /**
         * 根据题目id得到测试样例信息
         * @param id 题目id
         * @return 测试样例对象
         */
        TestSamples getTestSample(int id);

        /**
         * 得到题目的时间限制
         * @param id 题目id
         * @return 时间限制,单位秒
         */
        int getQuestionTimeLimit(int id);

        /**
         * 而得到题目的空间限制
         * @param id 题目id
         * @return 空间限制单位kb
         */
        int getQuestionMemoryLimit(int id);

        List<Question> searchQuestion(int page,String search);

        boolean addTotalCount(int totalPass,int totalAttempt,int id);

        String getQuestionDifficulty(int qid);

        List<Question> getQuestionSelectEasy();
        List<Question> getQuestionSelectMeddle();

        List<Question> getQuestionSelectHard();

        List<Question> getQuestionSelectNightMare();

        List<Question> getMatchQuestion(List<Integer> qid);

        /**
         * 统计所有题目数量
         * @return
         */
        int countQuestion();

        List<String> getMatchQuestionIds(int mid);
}
