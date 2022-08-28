package com.atguigu.gmall.product.service;


import com.atguigu.gmall.model.product.SpuInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author zhangxingyu
* @description 针对表【spu_info(商品表)】的数据库操作Service
* @createDate 2022-08-24 02:11:19
*/
public interface SpuInfoService extends IService<SpuInfo> {

    /*
        Spu信息存储
     */
    void saveSpuInfo(SpuInfo info);
}
