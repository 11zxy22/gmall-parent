package com.atguigu.gmall.web.feign;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.to.CategoryTreeTo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author zhangxingyu
 * @create 2022/8/28  20:06
 * @description
 */
@FeignClient("service-product")
//告诉springboot是一个远程调用接口
//远程调用之前feign会自己找nacos要到 service-product 真的地址
public interface CategoryFeignClient {

    /**
     *1、 给 service-product 发送一个 GET方式的请求
     *    路径是 /api/inner/rpc/product/category/tree
     *2、 拿到远程的响应 json 结果后转成 Result类型的对象，
     *    并且 返回的数据是 List<CategoryTreeTo>
     * @return
     *
     *   简单做法
     *   把远程的controller方法签名完全复制粘贴过来即可
     */

    @GetMapping("/api/inner/rpc/product/category/tree") //发送请求
    Result<List<CategoryTreeTo>> getCategoryTree();
}
