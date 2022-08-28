package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseSaleAttr;
import com.atguigu.gmall.product.service.BaseSaleAttrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhangxingyu
 * @create 2022/8/25  22:12
 * @description
 */
@RestController
@Api(tags = "销售API")
@RequestMapping("/admin/product")
public class BaseSaleAttrController {

    @Autowired
    BaseSaleAttrService baseSaleAttrService;

    /*
    获取销售属性
    */
    @ApiOperation(value = "获取销售属性")
    @GetMapping("/baseSaleAttrList")
    public Result baseSaleAttrList(){
        List<BaseSaleAttr> list = baseSaleAttrService.list();
        return Result.ok(list);
    }
}
