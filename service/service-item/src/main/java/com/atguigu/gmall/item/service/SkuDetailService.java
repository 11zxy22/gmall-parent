package com.atguigu.gmall.item.service;

import com.atguigu.gmall.model.to.SkuDetailTo;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zhangxingyu
 * @create 2022/8/28  22:29
 * @description
 */
public interface SkuDetailService {
    /*
        查询商品详情
     */
    SkuDetailTo getSkuDetail(Long skuId);
}
