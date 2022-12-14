package com.atguigu.gmall.product.mapper;


import com.atguigu.gmall.model.product.SkuInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
* @author zhangxingyu
* @description 针对表【sku_info(库存单元表)】的数据库操作Mapper
* @createDate 2022-08-24 02:11:18
* @Entity com.atguigu.gmall.product.domain.SkuInfo
*/
public interface SkuInfoMapper extends BaseMapper<SkuInfo> {

    /*
        更新sku的is_sale字段
     */
    void updateIsSale(@Param("skuId") Long skuId,
                      @Param("sale") int sale);

    BigDecimal getPrice(@Param("skuId") Long skuId);
}




