package com.ckw.common.controller;

import com.ckw.common.config.MinioConfig;
import com.ckw.common.pojo.Message;
import com.ckw.common.pojo.State;
import com.ckw.common.utils.MinioUtil;
import com.ckw.common.utils.SystemInfoUtils;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.UnknownHostException;
import java.util.UUID;

@RestController
@Slf4j
public class FileController {


    @Autowired
    private MinioConfig  minioConfig;

    @Autowired
    private MinioUtil minioUtil;
    @PostMapping("/uploadImg")
    public Object saveImage(@RequestParam("file") MultipartFile file){
        System.out.println(" ============================ > " + file);
        return minioConfig.getUrlPrefix() + minioUtil.upload(file);
    }

    @PostMapping("/download")
    public Object download(String fileName){
        return null;
    }

    @PostMapping("/removeFile/{fileName}")
    public Object removeFile(@PathVariable String fileName){
        boolean isRemove = minioUtil.remove(fileName);
        return new Message(isRemove ? State.SUCCESS : State.FAILURE,
                isRemove,isRemove ? "删除成功!" : "删除失败!"
                );
    }

    @PostMapping("/deleteImg")
    public Object deleteImg(@RequestParam("fileName") String fileName){
        System.out.println(fileName);
        boolean flag = minioUtil.remove(fileName.split("/online-image-bed/")[1]);
        return new Message(flag ? State.SUCCESS : State.FAILURE,flag,flag?"删除成功!":"删除失败!");
    }


}
