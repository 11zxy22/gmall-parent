package com.atguigu.gmall.product.service;


import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author zhangxingyu
* @description 针对表【spu_sale_attr(spu销售属性)】的数据库操作Service
* @createDate 2022-08-24 02:11:19
*/
public interface SpuSaleAttrService extends IService<SpuSaleAttr> {

    /*
    根据spuId获得销售属性名和值
     */
    List<SpuSaleAttr> getSaleAttrAndValueBySpuId(Long spuId);

    /*
    查询当前sku对应的spu定义的所有销售属性的名和值（固定好顺序） 并且标记当前sku属于哪一种组合
     */
    List<SpuSaleAttr> getSaleAttrAndValueMarkSku(Long spuId, Long skuId);

    /*
       商品sku的所有兄弟产品的销售属性名和值的组合关系并封装成{“119|120”：“50”}
     */
    String getAllSkuSaleAttrValueJson(Long spuId);

}
