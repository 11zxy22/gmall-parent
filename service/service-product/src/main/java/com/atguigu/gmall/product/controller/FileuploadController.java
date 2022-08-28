package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.product.service.FileUploadService;
import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import io.minio.errors.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangxingyu
 * @create 2022/8/24  23:29
 * @description 文件上传
 */
@Api(tags = "文件上传")
@RestController
@RequestMapping("/admin/product")
public class FileuploadController {

    @Autowired
    FileUploadService fileUploadService;


    /*
        文件上传功能：
        post请求体中包含文件流
        @RequestParam 接普通请求参数 （pojo）
        @RequestPart 接请求参数的文件项
        @RequestBody 接请求体中的所有数据
        @PathVariable 接路径上的请求参数
        @RequestHeader  获取浏览器请求的请求头
        @CookieValue    获取cookie中jsessionid

        如果数据有多个，使用数组接收

     */
    @ApiOperation(value = "品牌图片上传")
    @PostMapping("/fileUpload")
    public Result fileupload(@RequestPart("file")MultipartFile file) throws Exception {

        String url = fileUploadService.upload(file);

        return Result.ok(url);
    }

    /*
        接收数据测试
    <form action="http://localhost:9000/admin/product/reg" enctype="multipart/form-data"
                    method="post">
        头像：<input type="file" name="toux" /><br/>
        照片：<input type="file" name="zhaop" multiple/><br/>
        身份证：<input type="file" name="sfz"/><br/>
        用户名：<input type="text" name="username"/><br/>
        密码：<input type="text" name="password"/><br/>
        邮箱：<input type="text"name="email"/><br/>
        爱好：篮球<input type="checkbox" name="hobby" value="篮球"/>,
        足球<input type="checkbox" name="hobby" value="足球"/>
        <button>注册</button>
    </form>
     */
    @ApiOperation(value = "数据接收测试")
    @PostMapping("reg")
    public Result fileupload(@RequestParam("username")String username,
                             @RequestParam("password")String password,
                             @RequestParam("email")String email,
                             @RequestPart("toux")MultipartFile toux,
                             @RequestPart("zhaop")MultipartFile[] zhaop,
                             @RequestPart("sfz")MultipartFile sfz,
                             @RequestParam("hobby")String[] hobby,
                             @RequestHeader("Cache-Control")String cache,
                             @CookieValue("jsessionid")String jsessionid){
        Map<String, Object> map = new HashMap<>();
        map.put("用户名", username);
        map.put("密码", password);
        map.put(("邮箱"), email);

        map.put("头像文件大小",toux.getSize());
        map.put("生活照文件大小",zhaop.length);
        map.put("身份证文件大小",sfz.getSize());

        map.put("爱好", hobby);
        map.put("请求头", cache);
        map.put("jsessionid", jsessionid);


        return  Result.ok(map);
    }
}
