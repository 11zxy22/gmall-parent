package com.atguigu.gmall.item.service.impl;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.item.feign.SkuDetailFeignClient;
import com.atguigu.gmall.item.service.SkuDetailService;
import com.atguigu.gmall.model.product.SkuImage;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.model.to.CategoryViewTo;
import com.atguigu.gmall.model.to.SkuDetailTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

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
//        Result<SkuDetailTo> skuDetail = skuDetailFeignClient.getSkuDetail(skuId);
//        SkuDetailTo skuDetailData = skuDetail.getData();
        /*
            优化： 拆分单一职责

         */
        //1.查商品的基本信息
        Result<SkuInfo> result = skuDetailFeignClient.getSkuInfo(skuId);
        SkuInfo skuInfo = result.getData();
        skuDetailTo.setSkuInfo(skuInfo);
        //2.查商品图片信息
        Result<List<SkuImage>> images = skuDetailFeignClient.getSkuImages(skuId);
        skuInfo.setSkuImageList(images.getData());
        //3.查商品实时价格
        Result<BigDecimal> skuPrice = skuDetailFeignClient.getSkuPrice(skuId);
        skuDetailTo.setPrice(skuPrice.getData());
        //4.查销售属性名和值
        Result<List<SpuSaleAttr>> saleAttrAndValueMarkSku = skuDetailFeignClient.getSaleAttrAndValueMarkSku(skuInfo.getSpuId(), skuId);
        skuDetailTo.setSpuSaleAttrList(saleAttrAndValueMarkSku.getData());
        //5.查sku组合
        Result<String> allSkuSaleAttrValueJson = skuDetailFeignClient.getAllSkuSaleAttrValueJson(skuInfo.getSpuId());
        skuDetailTo.setValuesSkuJson(allSkuSaleAttrValueJson.getData());
        //6.查分类
        Result<CategoryViewTo> categoryView = skuDetailFeignClient.getCategoryView(skuInfo.getCategory3Id());
        skuDetailTo.setCategoryView(categoryView.getData());
        return skuDetailTo;
    }
}
