package com.atguigu.gmall.product.api;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.SkuImage;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.model.to.CategoryViewTo;
import com.atguigu.gmall.product.service.BaseCategory3Service;
import com.atguigu.gmall.product.service.SkuInfoService;
import com.atguigu.gmall.product.service.SpuSaleAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zhangxingyu
 * @create 2022/8/28  22:45
 * @description 商品详情数据库层操作
 */
@RestController
@RequestMapping("/api/inner/rpc/product")
public class SkuDetailApiController {

    @Autowired
    SkuInfoService skuInfoService;

    @Autowired
    SpuSaleAttrService spuSaleAttrService;

    @Autowired
    BaseCategory3Service baseCategory3Service;
//    @GetMapping("/skudetail/{skuId}")
//    public Result<SkuDetailTo> getSkuDetail(@PathVariable("skuId") Long skuId) {
//        SkuDetailTo skuDetailTo = skuInfoService.getSkuDetail(skuId);
//        System.out.println(skuDetailTo);
//        return Result.ok(skuDetailTo);
//    }

    /*
        查询商品基本信息
     */
    @GetMapping("/skudetail/info/{skuId}")
    public Result<SkuInfo> getSkuInfo(@PathVariable("skuId") Long skuId) {
        SkuInfo skuInfo = skuInfoService.getSkuInfo(skuId);
        return Result.ok(skuInfo);
    }

    /*
        查询商品图片信息
     */
    @GetMapping("/skudetail/image/{skuId}")
    public Result<List<SkuImage>> getSkuImages(@PathVariable("skuId") Long skuId) {
        List<SkuImage> imageList = skuInfoService.getSkuImages(skuId);
        return Result.ok(imageList);
    }

    /*
        查询商品的实时价格
     */
    @GetMapping("/skudetail/price/{skuId}")
    public Result<BigDecimal> getSkuPrice(@PathVariable("skuId") Long skuId) {
        BigDecimal price = skuInfoService.get1010Price(skuId);
        return Result.ok(price);
    }

    /*
        查询商品销售属性名和值
     */
    @GetMapping("/skudetail/saleattrvalues/{spuId}/{skuId}")
    public Result<List<SpuSaleAttr>> getSaleAttrAndValueMarkSku(@PathVariable("spuId") Long spuId,
                                                                @PathVariable("skuId") Long skuId) {
        List<SpuSaleAttr> saleAttrList = spuSaleAttrService.getSaleAttrAndValueMarkSku(spuId, skuId);
        return Result.ok(saleAttrList);
    }

    /*
        查询商品sku组合：  valueJson
     */
    @GetMapping("/skudetail/valuejson/{spuId}")
    public Result<String> getAllSkuSaleAttrValueJson(@PathVariable("spuId") Long spuId) {
        String valueJson = spuSaleAttrService.getAllSkuSaleAttrValueJson(spuId);
        return Result.ok(valueJson);
    }

    /*
        查询所有分类信息
     */
    @GetMapping("/skudetail/categoryview/{c3Id}")
    public Result<CategoryViewTo> getCategoryView(@PathVariable("c3Id") Long c3Id) {
        CategoryViewTo categoryViewTo = baseCategory3Service.getCategoryView(c3Id);
        return Result.ok(categoryViewTo);
    }
}
