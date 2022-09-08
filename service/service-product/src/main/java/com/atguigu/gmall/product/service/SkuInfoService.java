package com.atguigu.gmall.product.service;


import com.atguigu.gmall.model.product.SkuImage;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.to.SkuDetailTo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;
import java.util.List;

/**
* @author zhangxingyu
* @description 针对表【sku_info(库存单元表)】的数据库操作Service
* @createDate 2022-08-24 02:11:18
*/
public interface SkuInfoService extends IService<SkuInfo> {

    /*
    保存sku
     */
    void saveSkuInfo(SkuInfo info);

    /*
    下架
     */
    void cancelSale(Long skuId);
    /*
    上架
     */
    void onSale(Long skuId);

    /*
    获取sku商品详情
     */
    SkuDetailTo getSkuDetail(Long skuId);

    /*
        实时价格查询
     */
    BigDecimal get1010Price(Long skuId);

    /*
        查询sku基本信息
     */
    SkuInfo getSkuInfo(Long skuId);


    /*
        查询商品图片列表
     */
    List<SkuImage> getSkuImages(Long skuId);
}
