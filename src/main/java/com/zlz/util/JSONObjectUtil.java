package com.zlz.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zlz.ebook.UrlSetting;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class JSONObjectUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * JSON 2 Object
     * */
    public static <T> T toObject(String s, Class<T> T){
        try {
            return objectMapper.readValue(s, T);
        } catch (JsonProcessingException e) {
           throw new IllegalArgumentException(e);
        }
    }

    /**
     * JSON 2 Object
     * */
    public static <T> T toObject(JSONObject s, Class<T> T){
        try {
            return objectMapper.readValue(s.toString(), T);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }


    /**
     * List 2 String
     * */
    public static <T> String toJSONString(List<T> list) {
        JSONArray jsonArray = new JSONArray(list);
        return jsonArray.toString();
    }


    /**
     * POJO 2 JSON
     * */
    public static JSONObject toJSON(Object object) {
        JSONObject json = new JSONObject(object);
        return json;
    }



    /**
     * POJO 2 Map
     * */
    public static Map<String, Object> toMap(Object object) {
        JSONObject json = new JSONObject(object);
        return json.toMap();
    }


    /**
     * POJO 2 Map
     * */
    public static Map<String, Object> toMap(JSONObject object) {
        return object.toMap();
    }



}
