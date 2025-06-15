package cn.lyky.oa.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class ResponseUtilsTest {

    @Test
    public void put() {
        ResponseUtils resp = new ResponseUtils("LoginException", "密码错误")
                .put("class", "xxxClass")
                .put("name", "imooc");
        String json = resp.toJsonString();
        System.out.println(json);
    }
}