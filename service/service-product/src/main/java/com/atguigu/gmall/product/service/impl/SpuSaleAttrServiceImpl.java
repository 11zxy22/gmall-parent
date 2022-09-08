package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.common.util.Jsons;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.model.to.ValueSkuJsonTo;
import com.atguigu.gmall.product.mapper.SpuSaleAttrMapper;
import com.atguigu.gmall.product.service.SpuSaleAttrService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author zhangxingyu
* @description 针对表【spu_sale_attr(spu销售属性)】的数据库操作Service实现
* @createDate 2022-08-24 02:11:19
*/
@Service
public class SpuSaleAttrServiceImpl extends ServiceImpl<SpuSaleAttrMapper, SpuSaleAttr>
    implements SpuSaleAttrService{

    @Autowired
    SpuSaleAttrMapper spuSaleAttrMapper;
    @Override
    public List<SpuSaleAttr> getSaleAttrAndValueBySpuId(Long spuId) {
        List<SpuSaleAttr> saleAttrList = spuSaleAttrMapper.getSaleAttrAndValueBySpuId(spuId);
        return saleAttrList;
    }

    @Override
    public List<SpuSaleAttr> getSaleAttrAndValueMarkSku(Long spuId, Long skuId) {
        return spuSaleAttrMapper.getSaleAttrAndValueMarkSku(spuId, skuId);
    }

    @Override
    public String getAllSkuSaleAttrValueJson(Long spuId) {

        List<ValueSkuJsonTo> valueSkuJsonTos = spuSaleAttrMapper.getAllSkuSaleAttrValueJson(spuId);
        Map<String, Long> map = new HashMap<>();
        for (ValueSkuJsonTo valueSkuJsonTo : valueSkuJsonTos) {
            String valueJson = valueSkuJsonTo.getValueJson();
            Long skuId = valueSkuJsonTo.getSkuId();
            map.put(valueJson, skuId);
        }
        String json = Jsons.toStr(map);
        System.out.println(json);
        return json;
    }
}




