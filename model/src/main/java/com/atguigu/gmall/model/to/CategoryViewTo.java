package com.atguigu.gmall.model.to;

import lombok.Data;

/**
 * @author zhangxingyu
 * @create 2022/8/28  22:24
 * @description
 */
@Data
public class CategoryViewTo {
    private Long category1Id;
    private String  category1Name;
    private Long category2Id;
    private String category2Name;
    private Long category3Id;
    private String  category3Name;
}
