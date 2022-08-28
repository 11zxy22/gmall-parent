package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.BaseAttrValue;
import com.atguigu.gmall.product.mapper.BaseAttrInfoMapper;
import com.atguigu.gmall.product.mapper.BaseAttrValueMapper;
import com.atguigu.gmall.product.service.BaseAttrInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author zhangxingyu
* @description 针对表【base_attr_info(属性表)】的数据库操作Service实现
* @createDate 2022-08-24 02:11:18
*/
@Service
public class BaseAttrInfoServiceImpl extends ServiceImpl<BaseAttrInfoMapper, BaseAttrInfo>
    implements BaseAttrInfoService{

    @Autowired
    BaseAttrInfoMapper baseAttrInfoMapper;

    @Autowired
    BaseAttrValueMapper baseAttrValueMapper;
    @Override
    public List<BaseAttrInfo> getAttrInfoAndValueByCategoryId(Long category1Id, Long category2Id, Long category3Id) {

        /*
            查询指定分类下所有属性名和值
         */
        List<BaseAttrInfo> infos =baseAttrInfoMapper.getAttrInfoAndValueByCategoryId(category1Id, category2Id, category3Id);


        return infos;
    }

    @Override
    public void saveAttrInfo(BaseAttrInfo info) {
        if (info.getId() == null){
            //进行新增
            addBaseAttrInfo(info);
        }else {
            //进行修改
            updateBaseAttrInfo(info);
        }
    }

    private void updateBaseAttrInfo(BaseAttrInfo info) {
        //1.修改属性名
        baseAttrInfoMapper.updateById(info);
        //2.修改属性值
        //两种思路：①老记录全删，重新填装新记录，可能导致引用失效
        //          ②有id的进行覆盖修改，没有id的新增 前端不提交则代表已经删除
        List<BaseAttrValue> valueList = info.getAttrValueList();

        //先删除，避免造成删除新增的错误
        //delete * from base_attr_value where attr_id = 11 and not in
        List<Long> vids = new ArrayList<>();
        for (BaseAttrValue attrValue : valueList) {
            Long id = attrValue.getId();
            if (id!=null){
                vids.add(id);
            }
        }
        //非空判断
        if (vids.size()>0) {
            //代表部分删除
            QueryWrapper<BaseAttrValue> delete = new QueryWrapper<>();
            delete.eq("attr_id", info.getId());
            delete.notIn("id", vids);
            baseAttrValueMapper.delete(delete);
        }else {
            //代表全部删除
            QueryWrapper<BaseAttrValue> delete = new QueryWrapper<>();
            delete.eq("attr_id", info.getId());
            baseAttrValueMapper.delete(delete);
        }
        for (BaseAttrValue attrvalue:valueList) {

            if (attrvalue.getId() != null){
                //属性值有id 说明之前有，此次进行修改操作
                baseAttrValueMapper.updateById(attrvalue);
            }
            if (attrvalue.getId() == null){
                //说明是新增
                attrvalue.setAttrId(info.getId()); //需要id回填
                baseAttrValueMapper.insert(attrvalue);
            }
        }
    }

    private void addBaseAttrInfo(BaseAttrInfo info) {
        //1.1保存属性名
        baseAttrInfoMapper.insert(info);
        //1.2查询保存的属性名的自增id,
        // mybatisPlus中自增id会自动回填到info
        Long id = info.getId();

        //2.保存属性值
        List<BaseAttrValue> attrValueList = info.getAttrValueList();
        for (BaseAttrValue value : attrValueList) {
            //回填属性名记录的自增id
            value.setAttrId(id);
            baseAttrValueMapper.insert(value);
        }
    }

}




