package com.atguigu.gmall.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author zhangxingyu
 * @description 把对象转为json字符串
 * @createDate 2022/9/6 20:04
 */
public class Jsons {
    private static  ObjectMapper mapper = new ObjectMapper();
    public static String toStr(Object object) {
        try {
            String s = mapper.writeValueAsString(object);
            return s;
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
