package org.kenshin.springbootminio.controller;

import io.minio.errors.*;
import org.kenshin.springbootminio.config.MinioConfig;
import org.kenshin.springbootminio.utils.MinioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
public class TestController {
    @Autowired
    MinioUtil minioUtil;

    @Autowired
    MinioConfig minioConfig;

    @PostMapping("upload")
    public String upload(@RequestParam("fileName") MultipartFile file){
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        minioUtil.putObject(minioConfig.getBucketName(), file, file.getOriginalFilename());
        return "ok";
    }
    @GetMapping("getUrl")
    public String getUrl() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return minioUtil.getPresignedObjectUrl("test", "20200415210.png");
    }
}
