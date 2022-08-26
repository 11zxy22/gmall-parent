package com.atguigu.gmall.product;

import com.atguigu.gmall.common.config.Swagger2Config;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author zhangxingyu
 * @create 2022/8/23  9:16
 * @description
 * 默认只扫描 主程序所在的包和子包
 * 主程序 com.atguigu.gmall.product
 * 其他 com.atguigu.gmall.common.config
 * 1.批量导入 @ComponentScan("com.atguigu.gmall.common.config")
 * 2.批量导入 @SpringBootAppliaction(scanBasePackes = "com.atguigu.gmall")
 * 3.精准导入 @Import(Swagger2Config.class) 需要什么导入什么
 */


@Import(Swagger2Config.class)
@SpringCloudApplication
@MapperScan("com.atguigu.gmall.product.mapper")

public class ProductMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductMainApplication.class,args);
    }
}
