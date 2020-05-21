package com.laravelshao.learning.jdk.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qinghua.shao
 * @date 2017/8/25
 * @since 1.0.0
 */
public enum PriceHandleTypeEnum {

    ROUND_UP(1, "向上取整"),
    ROUND_DOWN(2, "向下取整"),
    ROUND_KEEP_ONE_DECIMAL(3, "四舍五入，保留一位小数"),
    NOT_ROUND_KEEP_ONE_DECIMAL(4, "不四舍五入，保留一位小数");

    private static Map<Integer, PriceHandleTypeEnum> enumMap;

    static {
        enumMap = new HashMap<>(values().length);
        PriceHandleTypeEnum[] enums = values();
        for (int i = 0; i < enums.length; i++) {
            PriceHandleTypeEnum e = enums[i];
            enumMap.put(e.code, e);
        }
    }

    private int code;

    private String value;

    PriceHandleTypeEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public static PriceHandleTypeEnum byCode(int code) {
        return enumMap.get(code);
    }
}
