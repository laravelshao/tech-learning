package com.laravelshao.learning.jdk.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @author qinghua.shao
 * @date 2017/8/25
 * @since 1.0.0
 */
public enum PriceHandleTypeEnum {

    ROUND_UP("1", "向上取整"),
    ROUND_DOWN("2", "向下取整"),
    ROUND_KEEP_ONE_DECIMAL("3", "四舍五入，保留一位小数"),
    NOT_ROUND_KEEP_ONE_DECIMAL("4", "不四舍五入，保留一位小数");

    private String code;

    private String value;

    PriceHandleTypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public static PriceHandleTypeEnum byCode(String code) {

        if (StringUtils.isEmpty(code)) {
            return null;
        } else {
            PriceHandleTypeEnum[] var1 = values();
            int var2 = var1.length;

            for (int var3 = 0; var3 < var2; ++var3) {
                PriceHandleTypeEnum e = var1[var3];
                if (e.getCode().equals(code)) {
                    return e;
                }
            }

            return null;
        }
    }

    /**
     * 根据CODE值返回VALUE
     *
     * @param code
     * @return
     */
    public static String returnValue(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        } else {
            PriceHandleTypeEnum[] var1 = values();
            int var2 = var1.length;

            for (int var3 = 0; var3 < var2; ++var3) {
                if (var1[var3].getCode().equals(code)) {
                    return var1[var3].getValue();
                }
            }

            return null;
        }
    }
}
