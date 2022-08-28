package com.atguigu.gmall.model.to;

import lombok.Data;

import java.util.List;

/**
 * @author zhangxingyu
 * @create 2022/8/28  18:56
 * @description 三级分类树形结构
 * DDD 领域驱动设计
 * 支持无线层级
 */
@Data
public class CategoryTreeTo {
    private Long categoryId;
    private String categoryName;
    private List<CategoryTreeTo> categoryChild;
}
