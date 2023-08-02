package com.ckw.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.ckw.common.pojo.system_info.*;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.UnknownHostException;
import java.util.List;

@SpringBootTest
public class SystemInfoUtilsTest {



    @Test
    public void testInfo(){
        ComputeInfo info = null;
        try {
            info = SystemInfoUtils.getInfo();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        System.out.println(info);

    }

}
