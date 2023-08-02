package com.ckw.common.docker;



import com.alibaba.fastjson.JSONObject;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.*;
import com.github.dockerjava.api.model.*;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.command.EventsResultCallback;
import com.github.dockerjava.core.command.ExecStartResultCallback;
import com.github.dockerjava.transport.DockerHttpClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;


/*
# 开放docker的对外服务端口
vim /lib/systemd/system/docker.service
ExecStart=/usr/bin/dockerd -H tcp://0.0.0.0:2375 -H unix://var/run/docker.sock
systemctl daemon-reload
systemctl restart docker
 */
@Component
@Slf4j
public class DockerClientUtils {



    @Autowired
    private DefaultDockerClientConfig defaultDockerClientConfig;

    @Autowired
    private DockerHttpClient dockerHttpClient;

    @Autowired
    private DockerClient dockerClient;

    /**
     * 查看镜像信息
     * @param imageName
     * @return
     */
    public String inspectImage(String imageName){
        InspectImageResponse response = dockerClient.inspectImageCmd(imageName).exec();
        return JSONObject.toJSONString(response);
    }

    /**
     * 移除镜像
     * @param imageName
     */
    public void removeImage(String imageName){
        dockerClient.removeImageCmd(imageName).exec();
    }

    /**
     * 获取镜像列表
     * @return
     */
    public List<Image> imageList(){
        return dockerClient.listImagesCmd().withShowAll(true).exec();
    }

    public CreateContainerResponse createContainer(String imageName){
        return  dockerClient.createContainerCmd(imageName).exec();
    }

    public CreateContainerResponse createContainer(String imageName,String hostPath,String containerPath,List<String> cmd){
        HostConfig hostConfig = new HostConfig();
        hostConfig.setBinds(new Bind(hostPath,new Volume(containerPath)));
        return  dockerClient.createContainerCmd(imageName)
                .withHostConfig(hostConfig)
                .withCmd(cmd)
                .exec();
    }

    public void runCommand(String containerId,String[] cmd){

        final ExecCreateCmdResponse execCreateCmdResponse = dockerClient
                .execCreateCmd(containerId)
                .withAttachStdout(true)
                .withCmd(cmd)
                .exec();
        try {
            dockerClient
                    .execStartCmd(execCreateCmdResponse.getId())
                    .exec(new ExecStartResultCallback(){
                        @Override
                        public void onNext(Frame frame) {
                            System.out.println(frame.toString());
                        }
                    })
                    .awaitCompletion(30, TimeUnit.NANOSECONDS);
        }catch (InterruptedException e){
            e.printStackTrace();
        }


    }




}
