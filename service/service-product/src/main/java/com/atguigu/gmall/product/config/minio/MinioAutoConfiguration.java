package com.atguigu.gmall.product.config.minio;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangxingyu
 * @create 2022/8/25  20:06
 * @description Minio自动配置类
 */
@Configuration
public class MinioAutoConfiguration {

    @Autowired
    MinioProperties minioProperties;

    @Bean
    public MinioClient minioClient() throws Exception {
        MinioClient minioClient =new MinioClient(
                minioProperties.getEndpoint(),
                minioProperties.getAccessKey(),
                minioProperties.getSecretKey());

        String bucket = minioProperties.getBucket();
        if (!minioClient.bucketExists(bucket)){
            minioClient.makeBucket(bucket);
        }
        return  minioClient;
    }
}
