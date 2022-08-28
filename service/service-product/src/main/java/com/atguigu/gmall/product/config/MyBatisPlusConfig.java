package com.atguigu.gmall.product.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author zhangxingyu
 * @create 2022/8/24  22:44
 * @description
 */
@EnableTransactionManagement //开启基于注解的事物
@Configuration //配置类
public class MyBatisPlusConfig {
    //1.插件主体放到容器中
    @Bean
    public MybatisPlusInterceptor interceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        //加入内部小插件
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        paginationInnerInterceptor.setOverflow(true); //页码溢出默认访问最后一页

        //分页插件
        interceptor.addInnerInterceptor(paginationInnerInterceptor);

        return interceptor;
    }
}
