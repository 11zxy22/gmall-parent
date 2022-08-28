package com.atguigu.gmall.product.service;


import com.atguigu.gmall.model.product.BaseAttrValue;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author zhangxingyu
* @description 针对表【base_attr_value(属性值表)】的数据库操作Service
* @createDate 2022-08-24 02:11:18
*/
public interface BaseAttrValueService extends IService<BaseAttrValue> {
    /*
        根据平台属性id查询出这个属性的所有属性值
     */
    List<BaseAttrValue> getAttrValueList(Long attrId);
}
