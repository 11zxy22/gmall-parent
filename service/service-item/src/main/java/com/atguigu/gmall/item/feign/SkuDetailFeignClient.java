package com.atguigu.gmall.item.feign;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.SkuImage;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.model.to.CategoryViewTo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zhangxingyu
 * @create 2022/8/28  23:15
 * @description
 */
@FeignClient("service-product")
@RequestMapping("/api/inner/rpc/product")
public interface SkuDetailFeignClient {

//    @GetMapping("/skudetail/{skuId}")
//    Result<SkuDetailTo> getSkuDetail(@PathVariable("skuId") Long skuId);

    @GetMapping("/skudetail/info/{skuId}")
    public Result<SkuInfo> getSkuInfo(@PathVariable("skuId") Long skuId);

    @GetMapping("/skudetail/image/{skuId}")
    public Result<List<SkuImage>> getSkuImages(@PathVariable("skuId") Long skuId);

    @GetMapping("/skudetail/price/{skuId}")
    public Result<BigDecimal> getSkuPrice(@PathVariable("skuId") Long skuId);

    @GetMapping("/skudetail/saleattrvalues/{spuId}/{skuId}")
    public Result<List<SpuSaleAttr>> getSaleAttrAndValueMarkSku(@PathVariable("spuId") Long spuId,
                                                                @PathVariable("skuId") Long skuId);

    @GetMapping("/skudetail/valuejson/{spuId}")
    public Result<String> getAllSkuSaleAttrValueJson(@PathVariable("spuId") Long spuId);

    @GetMapping("/skudetail/categoryview/{c3Id}")
    public Result<CategoryViewTo> getCategoryView(@PathVariable("c3Id") Long c3Id);
}
