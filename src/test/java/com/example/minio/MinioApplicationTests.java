package com.example.minio;

import com.example.minio.service.MinIOService;
import com.sun.source.tree.CompilationUnitTree;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLOutput;
import java.util.List;

@SpringBootTest
class MinioApplicationTests {

    @Resource
    private MinIOService minIOService;

    /**
     * 创建桶
     */
    @Test
    void createBucketIfNotExists() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        //桶是否存在
        System.out.println("当前桶是否存在"+minIOService.BuckExists("my-file1"));

        //创建桶
        minIOService.makeBucket("my-file1");

        //桶是否存在
        System.out.println("当前桶是否存在"+minIOService.BuckExists("my-file1"));

    }

    /**
     * 获取所有桶列表
     */
    @Test
    void getAllbucket() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        List<Bucket> list = minIOService.getAllBucket();
        list.forEach(bucket -> {
            System.out.println(bucket.name());
        });
    }

    /**
     * 删除桶
     */
    @Test
    void delBucket() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minIOService.removeBucket("abc");
    }

    /**
     * 上传文件到指定路径
     * 这里我们写死了，需要读者自己进行替换
     */
    @Test
    void putObject() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minIOService.putObject();
    }

    /**
     * 上传文件到指定路径
     */
    @Test
    void uploadObject() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minIOService.uploadObject();
    }

    /**
     * 检查文件是否存在
     */
    @Test
    void checkObject() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        System.out.println(minIOService.objectExists());
    }

    /**
     * 获取文件签名URL进行下载
     */
    @Test
    void getObjectUrl() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        System.out.println(minIOService.getObjectUrl());
    }
    /**
     * 下载文件
     */
    @Test
    void downloadObject() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minIOService.downloadObject();
    }

    /**
     * 获得桶中的所有文件
     */
    @Test
    void getAllObject() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minIOService.listObjects();

    }

    /**
     * 删除文件
     */
    @Test
    void removeObject() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minIOService.removeObject();
    }

}
