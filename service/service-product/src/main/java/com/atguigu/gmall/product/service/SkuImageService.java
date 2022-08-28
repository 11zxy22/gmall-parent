package com.atguigu.gmall.product.service;


import com.atguigu.gmall.model.product.SkuImage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author zhangxingyu
* @description 针对表【sku_image(库存单元图片表)】的数据库操作Service
* @createDate 2022-08-24 02:11:18
*/
public interface SkuImageService extends IService<SkuImage> {

    /*
        查询某个sku的图片列表
     */
    List<SkuImage> getImageListById(Long skuId);
}
