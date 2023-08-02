package com.ckw.common.utils;

import com.ckw.common.config.MinioConfig;
import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * minio 工具类
 * @author Echo
 */
@Component
@Slf4j
public class MinioUtil {

    @Autowired
    private MinioConfig minioConfig;

    @Resource
    private MinioClient minioClient;

    /**
     * 查看存储bucket是否存在
     * @param bucketName
     * @return
     */
    public boolean bucketExists(String bucketName){
        boolean found;
        try {
            found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return found;
    }

    /**
     * 创建存储bucket
     * @param bucketName
     * @return
     */
    public boolean createBucket(String bucketName){
        try {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 删除存储bucket
     * @return Boolean
     */
    public Boolean removeBucket(String bucketName) {
        try {
            minioClient.removeBucket(RemoveBucketArgs.builder()
                    .bucket(bucketName)
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /**
     * 获取全部bucket
     */
    public List<Bucket> getAllBuckets() {
        try {
            List<Bucket> buckets = minioClient.listBuckets();
            return buckets;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 上传文件
     * @param file
     * @return
     */
    public String upload(MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        if(originalFilename.isEmpty()){
            return null;
        }
        String fileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
        String objectName = DateUtils.getCurrentDate() + "/" + fileName;
        try {
            PutObjectArgs objectArgs = PutObjectArgs.builder().bucket(minioConfig.getBucketName())
                    .object(objectName)
                    .stream(file.getInputStream(),file.getSize(),-1).contentType(file.getContentType()).build();
//            文件名相同则覆盖
            minioClient.putObject(objectArgs);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return objectName;
    }

    /**
     * 预览图片
     * @param fileName
     * @return
     */
    public String preview(String fileName){
        GetPresignedObjectUrlArgs build = GetPresignedObjectUrlArgs.builder().bucket(minioConfig.getBucketName()).object(fileName).method(Method.GET).build();
        try {
            String url = minioClient.getPresignedObjectUrl(build);
            return url;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 文件下载
     * @param fileName
     * @param response
     */
    public void download(String fileName, HttpServletResponse  response){
        GetObjectArgs objectArgs = GetObjectArgs.builder().bucket(minioConfig.getBucketName()).object(fileName).build();
        try(GetObjectResponse objectResponse = minioClient.getObject(objectArgs)){
            byte[] buf = new byte[1024];
            int len;
            try(FastByteArrayOutputStream outputStream = new FastByteArrayOutputStream()){
                while((len = objectResponse.read(buf)) != -1){
                    outputStream.write(buf,0,len);
                }
                outputStream.flush();
                byte[] bytes = outputStream.toByteArray();
                response.setCharacterEncoding("utf-8");
//                设置强制下载不打开
                try(ServletOutputStream stream = response.getOutputStream()){
                    stream.write(bytes);
                    stream.flush();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 查问文件对象
     * @return 得到bucket内文件对象信息
     */
    public List<Item> listObjects(){
        Iterable<Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder().bucket(minioConfig.getBucketName()).build());
        List<Item> items = new ArrayList<>();
        try {
            for (Result<Item> result : results) {
                items.add(result.get());
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return items;
    }

    /**
     * 删除文件
     * @param fileName
     * @return
     */
    public boolean remove(String fileName){
        try {
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(minioConfig.getBucketName()).object(fileName).build());
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
