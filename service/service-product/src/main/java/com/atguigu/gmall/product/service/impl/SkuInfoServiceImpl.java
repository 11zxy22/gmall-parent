package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.*;
import com.atguigu.gmall.model.to.CategoryViewTo;
import com.atguigu.gmall.model.to.SkuDetailTo;
import com.atguigu.gmall.product.mapper.BaseCategory3Mapper;
import com.atguigu.gmall.product.mapper.SkuInfoMapper;
import com.atguigu.gmall.product.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
* @author zhangxingyu
* @description 针对表【sku_info(库存单元表)】的数据库操作Service实现
* @createDate 2022-08-24 02:11:18
*/
@Service
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoMapper, SkuInfo>
    implements SkuInfoService{

    @Autowired
    SkuImageService skuImageService;

    @Autowired
    SkuAttrValueService skuAttrValueService;

    @Autowired
    SkuSaleAttrValueService skuSaleAttrValueService;

    @Autowired
    SkuInfoMapper skuInfoMapper;

    @Autowired
    BaseCategory3Mapper baseCategory3Mapper;

    @Autowired
    SpuSaleAttrService spuSaleAttrService;

    /*
       sku_info : price skuName weight skuDesc category3Id skuDefaultImg tmId
       sku_image : skuImageList
       sku_attr_value : 保存当前sku平台属性名和值对应关系
       sku_sale_attr_value : 保存当前sku的销售属性名和值对应关系
     */
    @Transactional
    @Override
    public void saveSkuInfo(SkuInfo info) {
        //1.保存sku基本信息
        save(info);
        Long skuId = info.getId();
        //2.保存图片信息
        List<SkuImage> skuImageList = info.getSkuImageList();
        for (SkuImage skuImage : skuImageList) {
            skuImage.setSkuId(skuId);
        }
        skuImageService.saveBatch(skuImageList);
        //3.保存sku的平台属性的名和值
        List<SkuAttrValue> skuAttrValueList = info.getSkuAttrValueList();
        for (SkuAttrValue skuAttrValue : skuAttrValueList) {
            skuAttrValue.setSkuId(skuId);
        }
        skuAttrValueService.saveBatch(skuAttrValueList);
        //4.保存sku的销售属性的名和值
        List<SkuSaleAttrValue> skuSaleAttrValueList = info.getSkuSaleAttrValueList();
        for (SkuSaleAttrValue skuSaleAttrValue : skuSaleAttrValueList) {
            skuSaleAttrValue.setSkuId(skuId);
            skuSaleAttrValue.setSpuId(info.getSpuId());
        }
        skuSaleAttrValueService.saveBatch(skuSaleAttrValueList);
    }

    @Override
    public void cancelSale(Long skuId) {
        skuInfoMapper.updateIsSale(skuId,0);
        //TODO 2.从es中删除该商品
    }

    @Override
    public void onSale(Long skuId) {
        skuInfoMapper.updateIsSale(skuId,1);
        //TODO 2.在es中加入该商品
    }

    @Deprecated
    @Override
    public SkuDetailTo getSkuDetail(Long skuId) {
        SkuDetailTo detailTo = new SkuDetailTo();
        //首先查询skuInfo得到category3Id
        SkuInfo skuInfo = skuInfoMapper.selectById(skuId);
        //2.商品sku的基本信息
        detailTo.setSkuInfo(skuInfo);
        //3.商品sku的图片列表
        List<SkuImage> skuImageList = skuImageService.getImageListById(skuId);
        skuInfo.setSkuImageList(skuImageList);
        //1.商品sku所属的完整分类信息
        CategoryViewTo categoryViewTo = baseCategory3Mapper.getCategoryView(skuInfo.getCategory3Id());
        detailTo.setCategoryView(categoryViewTo);
        //实时价格查询
        BigDecimal price = get1010Price(skuId);
        detailTo.setPrice(price);
        //查询当前sku对应的spu定义的所有销售属性的名和值（固定好顺序） 并且标记当前sku属于哪一种组合
        List<SpuSaleAttr> saleAttrList = spuSaleAttrService.getSaleAttrAndValueMarkSku(skuInfo.getSpuId(), skuId);
        detailTo.setSpuSaleAttrList(saleAttrList);
        //商品sku的所有兄弟产品的销售属性名和值的组合关系并封装成{“119|120”：“50”}
        Long spuId = skuInfo.getSpuId();
        String valueJson = spuSaleAttrService.getAllSkuSaleAttrValueJson(spuId);
        detailTo.setValuesSkuJson(valueJson);
        return detailTo;
    }

    @Override
    public BigDecimal get1010Price(Long skuId) {
        BigDecimal price = skuInfoMapper.getPrice(skuId);
        return price;
    }

    @Override
    public SkuInfo getSkuInfo(Long skuId) {
        SkuInfo skuInfo = skuInfoMapper.selectById(skuId);
        return skuInfo;
    }

    @Override
    public List<SkuImage> getSkuImages(Long skuId) {
        List<SkuImage> skuImageList = skuImageService.getImageListById(skuId);
        return skuImageList;
    }
}




