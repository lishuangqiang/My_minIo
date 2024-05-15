package com.example.minio.config;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    //单例的，但是多线程访问下不存在线程安全性问题
    @Bean
    public MinioClient minioClient()
    {
        //MioIo的配置类需要使用他自己的构建类模式
        return  MinioClient.builder()
                .endpoint("http://localhost:9000")
                .credentials("minioadmin","minioadmin")
                .build();
    }
}
