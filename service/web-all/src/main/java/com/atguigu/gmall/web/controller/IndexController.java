package com.atguigu.gmall.web.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.to.CategoryTreeTo;
import com.atguigu.gmall.web.feign.CategoryFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author zhangxingyu
 * @create 2022/8/28  18:29
 * @description
 */
@Controller
public class IndexController {

    @Autowired
    CategoryFeignClient categoryFeignClient;

    @GetMapping("/")
    public String indexPage(Model model) {

        //查询出所有菜单  封装为一个树形结构模型
        Result<List<CategoryTreeTo>> result = categoryFeignClient.getCategoryTree();
        if (result.isOk()){ //返回是否是200
            List<CategoryTreeTo> data = result.getData();
            model.addAttribute("list", data);
        }

        // classpath: /templates/index/index.html
        return "index/index"; //页面的逻辑视图
    }

}
