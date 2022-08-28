package com.atguigu.gmall.item.service.impl;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.item.feign.SkuDetailFeignClient;
import com.atguigu.gmall.item.service.SkuDetailService;
import com.atguigu.gmall.model.to.SkuDetailTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangxingyu
 * @create 2022/8/28  22:29
 * @description
 *     /*
 *     远程查询商品详细信息
 *     包含：
 *         1、商品所属的完整分类 base_category123
 *         2、商品的基本信息（价格 重量） sku_info
 *         3、商品图片 sku_image
 *         4、商品所属spu的所有属性名和值  并表示当前sku是spu的哪一种组合
 *         5、商品类似推荐
 *         6、商品介绍
 *         7、商品规格参数
 *
 *  */

@Service
public class SkuDetailServiceImpl implements SkuDetailService {

    @Autowired
    SkuDetailFeignClient skuDetailFeignClient;
    @Override
    public SkuDetailTo getSkuDetail(Long skuId) {
        SkuDetailTo skuDetailTo = new SkuDetailTo();
        //远程调用商品服务查询
        Result<SkuDetailTo> skuDetail = skuDetailFeignClient.getSkuDetail(skuId);
        SkuDetailTo skuDetailData = skuDetail.getData();
        return skuDetailData;
    }
}
