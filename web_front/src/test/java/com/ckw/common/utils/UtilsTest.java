package com.ckw.common.utils;

import com.ckw.match.pojo.UserMatchingInfo;
import com.ckw.web_front.WebFrontApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = WebFrontApplication.class)
public class UtilsTest {


    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void testInput(){
        ArrayList<Integer> objects = new ArrayList<>();
        objects.add(10000);
        objects.add(10001);
        objects.add(10002);

//        redisUtils.updateMatchInfo("1@2",new UserMatchingInfo("1@2",objects));
    }
    @Test
    public void testOutput(){
        UserMatchingInfo userMatchingInfo = (UserMatchingInfo) redisUtils.getObject("1@2");
        System.out.println(userMatchingInfo);
    }
}
