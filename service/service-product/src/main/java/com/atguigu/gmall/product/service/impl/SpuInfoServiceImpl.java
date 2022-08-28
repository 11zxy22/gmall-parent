package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.SpuImage;
import com.atguigu.gmall.model.product.SpuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.model.product.SpuSaleAttrValue;
import com.atguigu.gmall.product.mapper.SpuImageMapper;
import com.atguigu.gmall.product.mapper.SpuSaleAttrMapper;
import com.atguigu.gmall.product.service.SpuImageService;
import com.atguigu.gmall.product.service.SpuSaleAttrService;
import com.atguigu.gmall.product.service.SpuSaleAttrValueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.atguigu.gmall.product.service.SpuInfoService;
import com.atguigu.gmall.product.mapper.SpuInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author zhangxingyu
* @description 针对表【spu_info(商品表)】的数据库操作Service实现
* @createDate 2022-08-24 02:11:19
*/
@Service
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoMapper, SpuInfo>
    implements SpuInfoService{

    @Autowired
    SpuInfoMapper spuInfoMapper;

    @Autowired
    SpuImageService spuImageService;
    
    @Autowired
    SpuSaleAttrService spuSaleAttrService;

    @Autowired
    SpuSaleAttrValueService spuSaleAttrValueService;

    @Transactional
    @Override
    public void saveSpuInfo(SpuInfo info) {
        // spu的基本数据保存到spu_info中
        spuInfoMapper.insert(info);
        Long spuId = info.getId();

        // spu的图片保存到spu_image(注意回填spuid)
        List<SpuImage> imageList = info.getSpuImageList();
        for (SpuImage spuImage : imageList) {
            //回填id
            spuImage.setSpuId(spuId);
        }
        //批量保存图片
        spuImageService.saveBatch(imageList);

        //保存销售属性名 spu_sale_attr
        // 保存销售属性值 spu_sale_attr_value
        List<SpuSaleAttr> attrNameList = info.getSpuSaleAttrList();
        for (SpuSaleAttr spuSaleAttr : attrNameList) {
            //回填id
            spuSaleAttr.setSpuId(spuId);
            //拿到销售属性值集合
            List<SpuSaleAttrValue> valueList = spuSaleAttr.getSpuSaleAttrValueList();
            for (SpuSaleAttrValue attrValue : valueList) {
                attrValue.setSpuId(spuId);
                String saleAttrName = spuSaleAttr.getSaleAttrName();
                attrValue.setSaleAttrName(saleAttrName);
            }
            //批量保存销售属性值
            spuSaleAttrValueService.saveBatch(valueList);
        }
        //批量保存
        spuSaleAttrService.saveBatch(attrNameList);
    }
}




