package com.example.minio.service;

import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class MinIOService {

    @Resource
    private MinioClient minioClient;

    public void testMinIoClient() {
        System.out.println(minioClient);


    }

    /**
     * 桶是否存在
     *
     * @param name
     */
    public Boolean BuckExists(String name) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        Boolean b = minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket(name).build());
        return b;
    }


    /**
     * 创建桶
     *
     * @param name
     */
    public void makeBucket(String name) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minioClient.makeBucket(MakeBucketArgs.builder()
                .bucket(name)
                .build());
    }

    /**
     * 获取所有桶列表
     */
    public List<Bucket> getAllBucket() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        List<Bucket> list = minioClient.listBuckets();
        return list;
    }

    /**
     * 删除桶
     */
    public void removeBucket(String name) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minioClient.removeBucket(RemoveBucketArgs.builder().bucket(name).build());
    }

    /**
     * 上传文件到指定存储桶
     */
    public void putObject() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        File file = new File("D:\\新年烟花\\A6849A8CBE9A4DEABCA0B9CE9CFCCF47.png");

        minioClient.putObject(PutObjectArgs.builder().bucket("my-file1")
                .object("电磁炮.png")
                //Stream的三个参数分别为  读取的文件  文件大小  缓冲区  （后两个参数可以都设置为-1，意思是minio自己决定）
                .stream(new FileInputStream(file), file.length(), -1)
                .build());
    }

    /**
     * 上传文件到指定存储桶
     */
    public void uploadObject() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minioClient.uploadObject(UploadObjectArgs.builder()
                .bucket("my-file1")
                .object("电磁炮2.png")
                .filename("D:\\新年烟花\\A6849A8CBE9A4DEABCA0B9CE9CFCCF47.png")
                .build());
    }

    /**
     * 检查当前对象是否在某个桶中存在
     */
    public Boolean objectExists() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        Boolean b = minioClient.statObject(StatObjectArgs.builder().
                bucket("my-file1")
                .object("电磁炮2.png")
                //对象是否为删除标记
                .build()).deleteMarker();
        return b;
    }

    /**
     * 获得对象URL
     */
    public String getObjectUrl() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        String url = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket("my-file1")
                .object("电磁炮2.png")
                .expiry(5, TimeUnit.DAYS).build());
        return url;
    }

    /**
     * 从指定桶中下载文件
     */
    public void downloadObject() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minioClient.downloadObject(DownloadObjectArgs.builder()
                .bucket("my-file1")
                .object("电磁炮2.png")
                .filename("D:\\新年烟花\\A6849A8CBE9A4DEABCA0B9CE9CFCCF47.png")
                .build());
    }

    /**
     * 查找桶中的所有文件
     */
    public void listObjects() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        Iterable<Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder()
                .bucket("my-file1")
                .build());
        results.forEach(itemResult -> {
            try {
                System.out.println(itemResult.get().objectName());
            } catch (ErrorResponseException e) {
                throw new RuntimeException(e);
            } catch (InsufficientDataException e) {
                throw new RuntimeException(e);
            } catch (InternalException e) {
                throw new RuntimeException(e);
            } catch (InvalidKeyException e) {
                throw new RuntimeException(e);
            } catch (InvalidResponseException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (ServerException e) {
                throw new RuntimeException(e);
            } catch (XmlParserException e) {
                throw new RuntimeException(e);
            }
        });

    }

    /**
     * 删除桶中指定元素
     */
    public  void removeObject() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minioClient.removeObject(RemoveObjectArgs.builder()
                .bucket("my-file1")
                .object("电磁炮2.png")
                .build());
    }

}
