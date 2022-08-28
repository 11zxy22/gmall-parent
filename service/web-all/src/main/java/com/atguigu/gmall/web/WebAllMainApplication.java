package com.atguigu.gmall.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zhangxingyu
 * @create 2022/8/27  1:52
 * @description 前端项目 数据跳转和页面渲染
 */
//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//@EnableDiscoveryClient
//@EnableCircuitBreaker
@SpringCloudApplication
@EnableFeignClients  //开启远程调用
public class WebAllMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebAllMainApplication.class, args);
    }
}
