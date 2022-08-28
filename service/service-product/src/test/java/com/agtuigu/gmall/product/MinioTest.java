package com.agtuigu.gmall.product;
import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import io.minio.errors.MinioException;
import org.junit.jupiter.api.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author zhangxingyu
 * @create 2022/8/25  9:20
 * @description 文件上传测试
 */
//@SpringBootTest
public class MinioTest {
    @Test
    public void testMinio(){
        try {
        // 使用MinIO服务的URL，端口，Access key和Secret key创建一个MinioClient对象
        MinioClient minioClient = new MinioClient("http://192.168.6.100:9000",
                                                    "admin",
                                                    "admin123456");

        // 检查存储桶是否已经存在
        boolean isExist = minioClient.bucketExists("gmall");
        if(isExist) {
            System.out.println("Bucket already exists.");
        } else {
            // 创建一个名为asiatrip的存储桶，用于存储照片的zip文件。
            minioClient.makeBucket("gmall");
        }

        // 使用putObject上传一个文件到存储桶中。
        // C:\Users\25352\Pictures\Camera Roll/风景1.jpg
            FileInputStream fileInputStream = new FileInputStream("D:\\尚硅谷220310Java\\09_JavaEE学科_尚品汇\\资料\\03 商品图片\\品牌\\pingguo.png");
            PutObjectOptions options = new PutObjectOptions(fileInputStream.available(),-1L);
            options.setContentType("image/png");
            //告诉minio内容类型
            minioClient.putObject("gmall",
                    "pingguo.png",
                    fileInputStream,
                    options);
        System.out.println("上传成功");
    } catch(
                MinioException | InvalidKeyException | IOException | NoSuchAlgorithmException e) {
        System.out.println("发生错误 " + e);
    }
        }
    }
