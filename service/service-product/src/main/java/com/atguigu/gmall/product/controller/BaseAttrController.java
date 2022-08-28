package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.BaseAttrValue;
import com.atguigu.gmall.product.service.BaseAttrInfoService;
import com.atguigu.gmall.product.service.BaseAttrValueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhangxingyu
 * @create 2022/8/24  2:04
 * @description 平台属性相关接口
 */
@Api(tags = "平台属性API")
@RestController
@RequestMapping("/admin/product")
public class BaseAttrController {

    @Autowired
    BaseAttrInfoService baseAttrInfoService;

    @Autowired
    BaseAttrValueService baseAttrValueService;

    /*
        根据分类id获得平台属性
        /admin/product/attrInfoList/{category1Id}/{category2Id}/{category3Id}
     */
    @ApiOperation(value = "根据分类id获得平台属性")
    @GetMapping("/attrInfoList/{category1Id}/{category2Id}/{category3Id}")
    public Result attrInfoList(@PathVariable("category1Id") Long category1Id,
                               @PathVariable("category2Id") Long category2Id,
                               @PathVariable("category3Id") Long category3Id) {
        List<BaseAttrInfo> baseAttrInfoList = baseAttrInfoService.getAttrInfoAndValueByCategoryId(category1Id, category2Id, category3Id);

        return Result.ok(baseAttrInfoList);
    }

    /*
        保存修改属性信息
        /admin/product/saveAttrInfo
        讲页面录入数据以json方式传给我们

        @requestBody 取出前端发送的请求的请求体中的数据
        并把这个数据（json）转成指定的BaseAttrInfo对象
        BaseAttrInfo封装前端提交来的所有数据
     */
    @ApiOperation(value = "新增和修改平台属性")
    @PostMapping("saveAttrInfo")
    public Result saveAttrInfo(@RequestBody BaseAttrInfo info) {
        //平台属性的新增
        baseAttrInfoService.saveAttrInfo(info);
        return Result.ok();
    }

    /*
        根据属性id获取属性信息并显示
        admin/product/getAttrValueList/11
     */
    @ApiOperation(value = "根据id查询属性信息")
    @GetMapping("/getAttrValueList/{attrId}")
    public Result getAttrValueList(@PathVariable("attrId") Long attrId) {
        List<BaseAttrValue> values = baseAttrValueService.getAttrValueList(attrId);
        return Result.ok(values);
    }
}




















