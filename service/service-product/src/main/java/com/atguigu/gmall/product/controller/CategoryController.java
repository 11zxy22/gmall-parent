package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseCategory1;
import com.atguigu.gmall.model.product.BaseCategory2;
import com.atguigu.gmall.model.product.BaseCategory3;
import com.atguigu.gmall.product.service.BaseCategory1Service;
import com.atguigu.gmall.product.service.BaseCategory2Service;
import com.atguigu.gmall.product.service.BaseCategory3Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhangxingyu
 * @create 2022/8/23  9:31
 * @description
 */
@RestController
@RequestMapping("/admin/product")
@Api(tags = "三级分类")
public class CategoryController {


    @Autowired
    BaseCategory1Service baseCategory1Service;

    @Autowired
    BaseCategory2Service baseCategory2Service;

    @Autowired
    BaseCategory3Service baseCategory3Service;

    /*
        获取所有的一级分类
     */
    @ApiOperation(value = "获取一级分类")
    @GetMapping("/getCategory1")
    public Result getCategory1() {
        List<BaseCategory1> category1List = baseCategory1Service.list();

        return Result.ok(category1List);
    }

    /*
       获取一级分类对应的二级分类
       /admin/product/getCategory2/2
     */
    @ApiOperation(value = "获取一级分类对应的二级分类")
    @GetMapping("/getCategory2/{c1id}")
    public Result getCategory2(@PathVariable("c1id") Long c1id) {
        List<BaseCategory2> category2List = baseCategory2Service.getCategory1Child(c1id);
        return Result.ok(category2List);
    }

    /*
        获取二级分类对应的三级分类
        /admin/product/getCategory3
     */
    @ApiOperation(value = "获取二级分类对应的三级分类")
    @GetMapping("/getCategory3/{c2id}")
    public Result getCategory3(@PathVariable("c2id") Long c2id) {
        List<BaseCategory3> category3List = baseCategory3Service.getCateGory2Child(c2id);
        return Result.ok(category3List);
    }
}
