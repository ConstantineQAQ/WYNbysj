package com.constantineqaq.base.response;


import com.alibaba.fastjson.JSONObject;
import com.constantineqaq.base.enums.CommonEnum;
import lombok.Data;

/**
 * @author wangyaning33
 */
public record HttpResponse<T>(int code, T data, String message) {

    public static <T> HttpResponse<T> success(T data) {
        return new HttpResponse<>(CommonEnum.SUCCESS.getCode(), data, CommonEnum.SUCCESS.getMessage());
    }

    public static <T> HttpResponse<T> failure(int code, String message) {
        return new HttpResponse<>(code, null, message);
    }

    public static <T> HttpResponse<T> failure(int code) {
        return failure(code, "请求失败");
    }

    public String asJsonString() {
        return JSONObject.toJSONString(this, true);
    }
}