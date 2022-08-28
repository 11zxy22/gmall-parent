package com.atguigu.gmall.item;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zhangxingyu
 * @create 2022/8/28  20:40
 * @description
 */
@SpringCloudApplication
@EnableFeignClients
public class ItemMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItemMainApplication.class, args);
    }
}
