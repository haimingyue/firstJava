package cn.lyky.oa.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseUtils {
    private String code;
    private String msg;
    private Map<String, Object> data = new LinkedHashMap<>();

    public ResponseUtils() {
        this.code = "0";
        this.msg = "success";
    }

    public ResponseUtils(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseUtils put(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public String toJsonString() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
             throw new RuntimeException(e);
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
