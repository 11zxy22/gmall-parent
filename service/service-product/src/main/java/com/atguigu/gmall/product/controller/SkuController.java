package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuImage;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.product.service.SkuAttrValueService;
import com.atguigu.gmall.product.service.SkuInfoService;
import com.atguigu.gmall.product.service.SpuImageService;
import com.atguigu.gmall.product.service.SpuSaleAttrService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhangxingyu
 * @create 2022/8/26  20:37
 * @description Sku接口
 */
@RestController
@Api(tags = "Sku接口")
@RequestMapping("/admin/product")
public class SkuController {

    @Autowired
    SkuInfoService skuInfoService;

    @Autowired
    SpuImageService spuImageService;

    @Autowired
    SpuSaleAttrService spuSaleAttrService;


    /*
        分页查询sku
        /admin/product/list/1/10
     */
    @ApiOperation(value = "分页查询sku")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Result getSkuList(@PathVariable("pageNum") Long pageNum,
                             @PathVariable("pageSize") Long pageSize) {
        Page<SkuInfo> page = new Page<>(pageNum, pageSize);
        Page<SkuInfo> infoPage = skuInfoService.page(page);
        return Result.ok(infoPage);
    }

    /*
        spu图片列表查询
        /admin/product/spuImageList/29
     */
    @ApiOperation(value = "根据spuId查询图片列表")
    @GetMapping("/spuImageList/{spuId}")
    public Result getSpuImageListBySpuId(@PathVariable("spuId") Long spuId) {
        QueryWrapper<SpuImage> wrapper = new QueryWrapper<>();
        wrapper.eq("spu_id", spuId);
        List<SpuImage> spuImageList = spuImageService.list(wrapper);
        return Result.ok(spuImageList);
    }

    /*
        根据spuId获得销售属性名和值
        /admin/product/spuSaleAttrList/29
     */
    @ApiOperation(value = "根据spuId获得销售属性名和值")
    @GetMapping("/spuSaleAttrList/{spuId}")
    public Result getSpuSaleAttrListBySpuId(@PathVariable("spuId") Long spuId) {

        List<SpuSaleAttr> spuSaleAttrList = spuSaleAttrService.getSaleAttrAndValueBySpuId(spuId);

        return Result.ok(spuSaleAttrList);
    }

    /*
        保存sku
        admin/product/saveSkuInfo
        SkuInfo可以根据json数据逆向生成
     */

    @ApiOperation(value = "保存sku")
    @PostMapping("/saveSkuInfo")
    public Result saveSkuInfo(@RequestBody SkuInfo info) {
        skuInfoService.saveSkuInfo(info);
        return Result.ok();
    }

    /*
        上架（联动Es）
        /admin/product/onSale/{skuId}
     */

    @ApiOperation(value = "上架")
    @GetMapping("/onSale/{skuId}")
    public Result onSale(@PathVariable("skuId") Long skuId) {
        skuInfoService.onSale(skuId);
        return Result.ok();
    }

        /*
        下架（联动Es）
        /admin/product/onSale/{skuId}
     */

    @ApiOperation(value = "下架")
    @GetMapping("/cancelSale/{skuId}")
    public Result cancelSale(@PathVariable("skuId") Long skuId) {
        skuInfoService.cancelSale(skuId);
        return  Result.ok();
    }
}
