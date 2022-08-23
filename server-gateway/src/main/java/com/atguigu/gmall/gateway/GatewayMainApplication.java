package com.atguigu.gmall.gateway;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;


/**
 * @author zhangxingyu
 * @create 2022/8/23  1:41
 * @description 主启动类
 */
//@SpringBootApplication
//@EnableCircuitBreaker // 开启事物熔断降级 流量保护（1.导入jar包 2.加注解）
//@EnableDiscoveryClient //开启服务发现 （1.导入jar包 2.加注解）
@SpringCloudApplication
public class GatewayMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayMainApplication.class,args);
    }
}
