package com.atguigu.gmall.product.mapper;


import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.model.to.ValueSkuJsonTo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author zhangxingyu
* @description 针对表【spu_sale_attr(spu销售属性)】的数据库操作Mapper
* @createDate 2022-08-24 02:11:19
* @Entity com.atguigu.gmall.product.domain.SpuSaleAttr
*/
public interface SpuSaleAttrMapper extends BaseMapper<SpuSaleAttr> {

    /*
    根据spuId查询对应的所有属性名和值
     */
    List<SpuSaleAttr> getSaleAttrAndValueBySpuId(@Param("spuId") Long spuId);

    /*
    查询当前sku对应的spu定义的所有销售属性的名和值（固定好顺序） 并且标记当前sku属于哪一种组合
     */
    List<SpuSaleAttr> getSaleAttrAndValueMarkSku(@Param("spuId") Long spuId, @Param("skuId") Long skuId);

    /*
       商品sku的所有兄弟产品的销售属性名和值的组合关系并封装成{“119|120”：“50”}
     */
    List<ValueSkuJsonTo> getAllSkuSaleAttrValueJson(@Param("spuId") Long spuId);
}




