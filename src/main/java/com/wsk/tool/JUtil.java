package com.wsk.tool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * FastJson工具类
 */
public class JUtil {
    public static String objToString(Object object) {
        return JSON.toJSONString(object);
    }

    public static <T> String listToJson(List<T> ts) {
        String jsons = JSON.toJSONString(ts);
        return jsons;
    }

    public static <T> T strToObject(String str, Class<T> clazz) {
        return JSONObject.parseObject(str, clazz);
    }

    public static <T> List<T> strToList(String jsonString, Class<T> clazz) {
        List<T> ts = (List<T>) JSONArray.parseArray(jsonString, clazz);
        return ts;
    }
}