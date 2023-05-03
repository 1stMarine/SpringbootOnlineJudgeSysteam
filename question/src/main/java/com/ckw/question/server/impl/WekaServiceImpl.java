package com.ckw.question.server.impl;

import com.ckw.judger.pojo.SubmitRecord;
import com.ckw.question.mapper.RecordMapper;
import com.ckw.question.server.WekaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.SimpleKMeans;
import weka.core.*;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

import java.util.*;
@Service
public class WekaServiceImpl implements WekaService {

    @Autowired
    private RecordMapper recordMapper;



    @Override
    public Map<Integer, List<SubmitRecord>> cluster(List<String> texts, List<SubmitRecord> records, int k) {

        // 1. 创建文本数据的Attribute
        Attribute textAttribute = new Attribute("text", (ArrayList<String>) null);
        ArrayList<Attribute> attributes = new ArrayList<Attribute>();
        attributes.add(textAttribute);
        Instances instances = new Instances("textInstances", attributes, texts.size());
        instances.setClassIndex(0);
        // 3. 将文本字符串数组插入到数据集对象中
        for (String str : texts) {
            DenseInstance instance = new DenseInstance(1);
            instance.setValue(textAttribute, str);
            instances.add(instance);
        }




        try {
            //filter数据预处理
            StringToWordVector filter = new StringToWordVector();
            //指定Filter输入数据格式
            filter.setInputFormat(instances);
            //保留多少个单词 （每一类中出现次数前1000名的单词被保留）
            filter.setWordsToKeep(1000);
            //值为true时，把文档中单词出现的次数val（或者TFTransform转化后的值）转化为
            //val∗Math.log(doc_total_num/doc_num_contain_this_word)
            filter.setIDFTransform(true);
            //outputWordCounts:值为true时，记录单词在文档中出现的次数，而不是boolean值。
            filter.setOutputWordCounts(true);
            Instances dataFiltered = Filter.useFilter(instances, filter);
            //k-means
            SimpleKMeans skm = new SimpleKMeans();
            skm.setDisplayStdDevs(false);
            skm.setDistanceFunction(new EuclideanDistance());
            skm.setMaxIterations(500);
            skm.setDontReplaceMissingValues(true);
            skm.setNumClusters(k);
            skm.setPreserveInstancesOrder(false);
            skm.setSeed(100);
            skm.buildClusterer(dataFiltered);
            System.out.println(skm);
// 对聚类器进行评估
            ClusterEvaluation eval = new ClusterEvaluation();
            eval.setClusterer(skm);
            eval.evaluateClusterer(dataFiltered);



            // 遍历每个簇


//        词频
//        for (int j = 0; j < k; j++) {
//            String[] nums = skm.getClusterCentroids().get(j).toString().split(",");
//            System.out.println("第" + (j+1) + "个类");
//            for (int i = 0;i < skm.getClusterCentroids().numAttributes();i++){
//                System.out.println(skm.getClusterCentroids().attribute(i).toString().split(" ")[1] + " ,概率 " + nums[i]);
//            }
//        }

            Map<Integer, List<SubmitRecord>> hashMap = new HashMap<>();

            for (int i = 0; i < dataFiltered.numInstances(); i++) {

                if(hashMap.get(skm.clusterInstance(dataFiltered.get(i)) ) == null){
//                    新增arrayList
                    hashMap.put(skm.clusterInstance(dataFiltered.get(i)),new ArrayList<>());
                }
//                这个样本新增测试样例
                hashMap.get(skm.clusterInstance(dataFiltered.get(i))).add(records.get(i));
            }
            for (Integer integer : hashMap.keySet()) {
                if(hashMap.get(integer).size() <= 1){
                    hashMap.remove(integer);
                }
            }
            return hashMap;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }




    }
    @Override
    public int findBestK(List<String> texts) throws Exception {
        // 1. 创建文本数据的Attribute
        Attribute textAttribute = new Attribute("text", (ArrayList<String>) null);
        ArrayList<Attribute> attributes = new ArrayList<Attribute>();
        attributes.add(textAttribute);
        Instances data = new Instances("textInstances", attributes, texts.size());
        data.setClassIndex(0);
        // 3. 将文本字符串数组插入到数据集对象中
        for (String str : texts) {
            DenseInstance instance = new DenseInstance(1);
            instance.setValue(textAttribute, str);
            data.add(instance);
        }


        // Set up k-means clustering
        SimpleKMeans kMeans = new SimpleKMeans();
        kMeans.setSeed(new Random().nextInt(100));
        kMeans.setPreserveInstancesOrder(true);

        // Find best k value
        int maxK = Math.min(texts.size(), 10); // maximum k value to try
        double[] sumOfSquaredErrors = new double[maxK];
        for (int k = 1; k <= maxK; k++) {
            //filter数据预处理
            StringToWordVector filter = new StringToWordVector();
            //指定Filter输入数据格式
            filter.setInputFormat(data);
            //保留多少个单词 （每一类中出现次数前1000名的单词被保留）
            filter.setWordsToKeep(1000);
            //值为true时，把文档中单词出现的次数val（或者TFTransform转化后的值）转化为
            //val∗Math.log(doc_total_num/doc_num_contain_this_word)
            filter.setIDFTransform(true);
            //outputWordCounts:值为true时，记录单词在文档中出现的次数，而不是boolean值。
            filter.setOutputWordCounts(true);
            Instances dataFiltered = Filter.useFilter(data, filter);
            //k-means

            kMeans.setDisplayStdDevs(false);
            kMeans.setDistanceFunction(new EuclideanDistance());
            kMeans.setMaxIterations(500);
            kMeans.setDontReplaceMissingValues(true);
            kMeans.setNumClusters(k);
            kMeans.setPreserveInstancesOrder(false);

            kMeans.buildClusterer(dataFiltered);


            sumOfSquaredErrors[k-1] = kMeans.getSquaredError();

        }
        int bestK = 1;
        double minSSE = sumOfSquaredErrors[0];

        for (double sumOfSquaredError : sumOfSquaredErrors) {
            System.out.println(sumOfSquaredError);
        }
        /**
         * 在本方法中，我们使用的是“肘部法”（Elbow Method）来确定最佳的聚类数量k。
         *
         * 肘部法是一种常用的确定聚类数量的方法，它的基本思想是随着聚类数量k的增加，聚类效果会逐渐变好，但是当k增加到一定程度时，聚类效果的提升会逐渐变缓，直至趋于平稳。这个“肘部”就是指这个曲线的拐点，这个拐点就是最佳的聚类数量k。
         *
         * 在本方法中，我们尝试了1到10个聚类数量，对于每个聚类数量，我们使用K-Means算法进行聚类，并计算聚类结果的SSE（Sum of Squared Errors）。SSE是指每个点到其所属聚类中心的距离的平方和，SSE越小表示聚类效果越好。
         *
         * 然后我们将每个聚类数量的SSE绘制成曲线图，找到曲线的拐点，即可确定最佳的聚类数量k。
         *
         * 下面是一个示例图，其中红线是SSE曲线，蓝线是拐点所在的位置，即最佳的聚类数量k。
         * 12.500000000000004
         * 3.0000000000000004
         * 1.4999999999999998
         * 0.0
         * 0.0
         * Best K: 4
         *
         * 3 - 12 突然猛增，这是一个拐点
         */
        for (int k = 2; k <= maxK; k++) {
            if (sumOfSquaredErrors[k-1] < minSSE) {
                bestK = k;
                minSSE = sumOfSquaredErrors[k-1];
            }
        }

        return bestK;
    }

    @Override
    public Map<Integer, List<SubmitRecord>> calculate(int mid) {
//        查到某竞赛的全部提交记录
        Map<Integer, List<SubmitRecord>> cluster = new HashMap<>();

        List<SubmitRecord> allSubmitRecordList = recordMapper.getAllMatchSubmitRecordOnlyAC(mid);
        if(allSubmitRecordList.size() == 0){
            return cluster;
        }
        List<String> codes = new ArrayList<>();
//        剥离代码
        for (SubmitRecord submitRecord : allSubmitRecordList) {
            codes.add(submitRecord.getCode());
        }
        int bestK = 1;
        try {
//            计算最佳k
            bestK = findBestK(codes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

//        聚类

        cluster = cluster(codes, allSubmitRecordList, bestK);

//        for (Integer integer : cluster.keySet()) {
//            System.out.println("聚类 " + integer);
//            List<SubmitRecord> submitRecords = cluster.get(integer);
//            for (SubmitRecord submitRecord : submitRecords) {
//                System.out.println(submitRecord.getId());
//            }
//        }

        return cluster;
    }


    public static void main(String[] args) {

        String[] texts = new String[] {
                "The sky is blue.",
                "The sun is bright.",
                "The sun in the sky is bright.",
                "We can see the shining sun, the bright sun.",
                "We can see the shining sun, the bright sun."
        };

//        int bestK = 0;
//        try {
//            bestK = findBestK(Arrays.asList(texts));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("Best K: " + bestK);
//
//
//        int k = 30;


    }
}
