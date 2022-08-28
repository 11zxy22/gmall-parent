package com.atguigu.gmall.product.config.minio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhangxingyu
 * @create 2022/8/25  20:09
 * @description 配置属性类
 *
 *  功能扩展时，只需要修改配置文件和配置属性类
 */
@ConfigurationProperties(prefix = "app.minio")
@Component
@Data
public class MinioProperties {

    String endpoint;
    String accessKey;
    String secretKey;
    String bucket;


}
