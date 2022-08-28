package com.atguigu.gmall.product.service;


import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author zhangxingyu
* @description 针对表【base_attr_info(属性表)】的数据库操作Service
* @createDate 2022-08-24 02:11:18
*/
public interface BaseAttrInfoService extends IService<BaseAttrInfo> {

    /*
        根据三级id查询平台属性
     */
    List<BaseAttrInfo> getAttrInfoAndValueByCategoryId(Long category1Id, Long category2Id, Long category3Id);

    /*
        保存平台属性
     */
    void saveAttrInfo(BaseAttrInfo info);
}
