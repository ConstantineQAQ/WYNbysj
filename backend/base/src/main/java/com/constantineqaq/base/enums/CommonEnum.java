package com.constantineqaq.base.enums;

import lombok.Getter;

/**
 * @author wangyaning33
 */

@Getter
public enum CommonEnum {
    SUCCESS(200, "成功"),
    FAIL(500, "失败"),
    NOT_FOUND(404, "未找到");
    ;


    private final int code;
    private final String message;

    CommonEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }


}
