package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.common.util.DateUtil;
import com.atguigu.gmall.product.config.minio.MinioAutoConfiguration;
import com.atguigu.gmall.product.config.minio.MinioProperties;
import com.atguigu.gmall.product.service.FileUploadService;
import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;


/**
 * @author zhangxingyu
 * @create 2022/8/25  10:07
 * @description
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    MinioClient minioClient;

    @Autowired
    MinioProperties minioProperties;

    @Override
    public String upload(MultipartFile file) throws Exception {


        // 2.检查存储桶是否已经存在
        boolean isExist = minioClient.bucketExists(minioProperties.getBucket());
        if(!isExist) {
            minioClient.makeBucket(minioProperties.getBucket());
        }
        // 3.上传文件到桶
        String name = file.getName(); //input标签中的name值
        String date = DateUtil.formatDate(new Date());
        String filename = UUID.randomUUID().toString().replace("-","")
                +"_"+file.getOriginalFilename(); //原始文件名
        InputStream inputStream = file.getInputStream();
        long fileSize = file.getSize();
        String fileContentType = file.getContentType();
        PutObjectOptions options = new PutObjectOptions(fileSize, -1L);
        options.setContentType(fileContentType);

        // 4.文件上传
        minioClient.putObject(
                "gmall",
                date+"/"+filename,
                inputStream,
                options
                );
        System.out.println("上传成功");
        //5.返回url
        String url = minioProperties.getEndpoint()+"/"+minioProperties.getBucket()+"/"+date+"/"+filename;
        return url;
        /*
            优化思路：
                1.文件名覆盖问题
                2.桶内按日期存储
                3.配置抽取
         */
    }
}
