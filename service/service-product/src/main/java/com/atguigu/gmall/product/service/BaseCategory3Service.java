package com.atguigu.gmall.product.service;


import com.atguigu.gmall.model.product.BaseCategory3;
import com.atguigu.gmall.model.to.CategoryViewTo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author zhangxingyu
* @description 针对表【base_category3(三级分类表)】的数据库操作Service
* @createDate 2022-08-23 20:23:28
*/
public interface BaseCategory3Service extends IService<BaseCategory3> {

    /*
        查询二级分类下的三级子分类
     */
    List<BaseCategory3> getCateGory2Child(Long c2id);
    /*
    查分类
     */
    CategoryViewTo getCategoryView(Long c3Id);
}
