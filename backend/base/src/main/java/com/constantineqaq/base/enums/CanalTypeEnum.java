package com.constantineqaq.base.enums;

import lombok.Getter;

@Getter
public enum CanalTypeEnum {
    INSERT("INSERT", "插入"),

    UPDATE("UPDATE", "更新"),

    DELETE("DELETE", "删除"),
    ;

    private final String code;
    private final String desc;

    CanalTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static CanalTypeEnum getEnumByCode(String code) {
        for (CanalTypeEnum eventType : CanalTypeEnum.values()) {
            if (eventType.getCode().equalsIgnoreCase(code)) {
                return eventType;
            }
        }
        return null;
    }

}
