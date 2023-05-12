package com.ckw.question.utils;

import com.ckw.common.utils.SnowflakeIdWorker;
import com.ckw.question.pojo.Question;
import com.ckw.question.pojo.TestSamples;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author 凯威
 * 解析xml文件工具类
 */
public class xmlUtils {

    /**
     * 将xml题目文件解析封装成题目对象
     * @param path
     * @return
     */
    public static Question parseXml(String path){
        SAXReader reader = new SAXReader();
        Document doc = null;
        try {
            // 出现java.net.MalformedURLException: no protocol异常 ， 是因为使用了中文的文件名
            doc = reader.read(path);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        Element root = doc.getRootElement();
        String title = root.elementText("title");
        int timeLimit = Integer.parseInt(root.elementText("time_limit"));
        int memoryLimit = Integer.parseInt(root.elementText("memory_limit"));
        int aPrivate = Integer.parseInt(root.elementText("private"));
        String description = root.elementText("description");
        String inputStyle = root.elementText("input_style");
        String outputStyle = root.elementText("output_style");

        String sampleInputs = getXmlListToString(root.element("sample_inputs").elements());
        String sampleOutput = getXmlListToString(root.element("sample_outputs").elements());

        String dataRage = root.elementText("data_range");
        String resource = root.elementText("resource");

        String tag = getXmlListToString(root.element("tags").elements());

        String difficulty = root.elementText("difficulty");

        String testInputs = getXmlListToString(root.element("test_inputs").elements());
        String testOutputs = getXmlListToString(root.element("test_outputs").elements());



        System.out.println(aPrivate);

        int id = SnowflakeIdWorker.nextId();

        TestSamples testSamples = new TestSamples(
                0,
                id,
                title,
                testInputs,
                testOutputs
        );


        Question question = new Question(
                id,
                title,
                inputStyle,
                outputStyle,
                sampleInputs,
                sampleOutput,
                dataRage,
                difficulty,
                timeLimit,
                memoryLimit,
                description,
                0,0,
                resource,
                tag,
                testSamples,
                0.0,
                aPrivate,
                0
        );



        return question;
    }

    /**
     * 编码为json数组
     * @param list 组件集合
     * @return 编码后的json数组
     */
    public static String getXmlListToString(List<Element> list){
        String result = "[";

        for (int i = 0; i < list.size(); i++) {
            result += i == list.size()-1 ? "\"" + list.get(i).getText() + "\"" :
            "\"" + list.get(i).getText() + "\",";
        }
        result += "]";
        return result;
    }

}
