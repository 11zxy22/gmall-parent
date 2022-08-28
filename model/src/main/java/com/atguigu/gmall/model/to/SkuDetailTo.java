package com.atguigu.gmall.model.to;

import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zhangxingyu
 * @create 2022/8/28  21:14
 * @description
 */
@Data
public class SkuDetailTo {
    private CategoryViewTo categoryView;

    private SkuInfo skuInfo;

    private BigDecimal price;

    private List<SpuSaleAttr> spuSaleAttrList;

    private String valuesSkuJson;

}
