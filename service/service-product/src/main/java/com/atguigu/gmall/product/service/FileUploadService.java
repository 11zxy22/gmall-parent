package com.atguigu.gmall.product.service;

import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author zhangxingyu
 * @create 2022/8/25  10:06
 * @description
 */
public interface FileUploadService {

    /*
    文件上传返回url
     */
    String upload(MultipartFile file) throws Exception;
}
