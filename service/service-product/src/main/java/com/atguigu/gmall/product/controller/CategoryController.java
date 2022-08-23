package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseCategory1;
import com.atguigu.gmall.model.product.BaseCategory2;
import com.atguigu.gmall.product.service.BaseCategory1Service;
import com.atguigu.gmall.product.service.BaseCategory2Service;
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
public class CategoryController {


    @Autowired
    BaseCategory1Service baseCategory1Service;

    @Autowired
    BaseCategory2Service baseCategory2Service;
    /*
        获取所有的一级分类
     */
    @GetMapping("/getCategory1")
    public Result getCategory1(){
        List<BaseCategory1> category1List = baseCategory1Service.list();

        return Result.ok(category1List);
    }

    /*
       获取一级分类对应的二级分类
       /admin/product/getCategory2/2
     */
    @GetMapping("/getCategory2/{c1id}")
    public Result getCategory2(@PathVariable("c1id") Long c1id) {
        List<BaseCategory2> category2List = baseCategory2Service.getCategory1Child(c1id);
        return Result.ok(category2List);
    }
}