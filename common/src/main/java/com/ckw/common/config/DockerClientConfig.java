package com.ckw.common.config;

import com.alibaba.fastjson.JSONObject;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import io.minio.MinioClient;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.time.Duration;

@Data
@Configuration
@Slf4j
@ConfigurationProperties(prefix = "docker")
public class DockerClientConfig {

    private String host;

    private String apiVersion;

    @Bean
    public DefaultDockerClientConfig defaultDockerClientConfig(){
        log.info("dockerHost : " + host);
        return DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost(host)
                .withApiVersion(apiVersion)
                .withDockerTlsVerify(false).build();
    }

    @Bean
    public DockerHttpClient dockerHttpClient(){
        return new ApacheDockerHttpClient.Builder()
                .dockerHost(URI.create(host))
                .maxConnections(100)
                .connectionTimeout(Duration.ofSeconds(30))
                .responseTimeout(Duration.ofSeconds(45))
                .build();
    }

    @Bean
    public DockerClient dockerClient(){
        DockerClient dockerClient = DockerClientImpl.getInstance(defaultDockerClientConfig(), dockerHttpClient());
        Info info = dockerClient.infoCmd().exec();
        log.info("docker环境 ：" + JSONObject.toJSONString(info));
        return dockerClient;
    }
}
