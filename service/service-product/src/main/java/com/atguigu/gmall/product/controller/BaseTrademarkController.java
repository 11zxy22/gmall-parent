package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseTrademark;
import com.atguigu.gmall.product.service.BaseTrademarkService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhangxingyu
 * @create 2022/8/24  22:28
 * @description 品牌API
 */
@Api(tags = "品牌API")
@RestController
@RequestMapping("/admin/product")
public class BaseTrademarkController {

    @Autowired
    BaseTrademarkService baseTrademarkService;

    /*
        获取品牌分页列表
        /admin/product/baseTrademark/{page}/{limit}
     */
    @ApiOperation(value = "获取品牌分页列表")
    @GetMapping("/baseTrademark/{pageNum}/{pageSize}")
    public Result baseTrademark(@PathVariable("pageNum") Long pageNum,
                                @PathVariable("pageSize") Long pageSize) {
        Page<BaseTrademark> page = new Page<>(pageNum, pageSize);
        //分页查询
        //pageResult包括分页信息，查询到的记录集合
        Page<BaseTrademark> pageResult = baseTrademarkService.page(page);
        return Result.ok(pageResult);
    }

    /*
        根据id获取品牌
     */
    @ApiOperation(value = "根据id获取品牌")
    @GetMapping("/baseTrademark/get/{id}")
    public Result baseTrademarkGetById(@PathVariable("id") Long id) {
        BaseTrademark trademark = baseTrademarkService.getById(id);
        return Result.ok(trademark);
    }

    /*
        修改品牌
     */
    @ApiOperation(value = "修改品牌")
    @PutMapping("/baseTrademark/update")
    public Result updateBaseTrademark(@RequestBody BaseTrademark trademark) {
        baseTrademarkService.updateById(trademark);
        return Result.ok();
    }

    /*
        新增一个品牌
     */
    @ApiOperation(value = "新增品牌")
    @PostMapping("baseTrademark/save")
    public Result saveBasrTrademark(@RequestBody BaseTrademark trademark) {
        baseTrademarkService.save(trademark);
        return Result.ok();
    }

    /*
        删除品牌
     */
    @ApiOperation(value = "删除品牌")
    @DeleteMapping("baseTrademark/remove/{id}")
    public Result deleteTrademarkById(@PathVariable("id") Long id) {
        baseTrademarkService.removeById(id);
        return Result.ok();
    }

    /*
        获取品牌列表
     */
    @ApiOperation(value = "获取品牌列表")
    @GetMapping("/baseTrademark/getTrademarkList")
    public Result getTrademarkList(){
        List<BaseTrademark> trademarkList = baseTrademarkService.list();
        return Result.ok(trademarkList);
    }
}
