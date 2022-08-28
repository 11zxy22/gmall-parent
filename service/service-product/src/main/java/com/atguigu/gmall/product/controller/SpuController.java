package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseSaleAttr;
import com.atguigu.gmall.model.product.SpuInfo;
import com.atguigu.gmall.product.service.BaseSaleAttrService;
import com.atguigu.gmall.product.service.SpuInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhangxingyu
 * @create 2022/8/25  21:25
 * @description
 * @PathVariable 路径参数
 * @RequestParam 请求参数（请求体中的某个数据）
 * @RequestBody 请求参数（请求体中的全部数据）
 * <p>
 * 举例： http://192.168.6.100:9000/admin/product/1/10?Category3Id=2
 * @PathVariable 在?之前工作
 * RequestParam  在?之后工作
 * <p>
 * 如果是Post请求 请求参数既可以放到url?以后，也可以放到请求体
 * @RequestParam 都可以取
 * 如果是Get请求 请求参数需要放到url后面
 * @RequestParam 都可以取
 */
@Api(tags = "Spu接口")
@RestController
@RequestMapping("/admin/product")
public class SpuController {

    @Autowired
    SpuInfoService spuInfoService;

    @Autowired
    BaseSaleAttrService baseSaleAttrService;

    /*
        Spu分页查询
     */
    @ApiOperation(value = "Spu分页查询")
    @GetMapping("/{pageNum}/{pageSize}")
    public Result getSpuPage(@PathVariable("pageNum") Long pageNum,
                             @PathVariable("pageSize") Long pageSize,
                             @RequestParam(value = "category3Id", defaultValue = "0") Long category3Id) {
        Page<SpuInfo> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SpuInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("category3_id", category3Id);
        Page<SpuInfo> infoPage = spuInfoService.page(page, wrapper);
        return Result.ok(infoPage);
    }

    /*
        保存Spu
     */
    @ApiOperation(value = "保存Spu")
    @PostMapping("/saveSpuInfo")
    public Result saveSpuInfo(@RequestBody SpuInfo info){
        // spu_info  基本信息
        // spu_image       图片
        // spu_sale_attr    销售属性名
        // spu_sale_attr_value  销售属性值

        spuInfoService.saveSpuInfo(info);
        return  Result.ok();
    }
}
