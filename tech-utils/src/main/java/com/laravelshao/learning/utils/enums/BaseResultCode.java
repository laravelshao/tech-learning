package com.laravelshao.learning.utils.enums;

import com.laravelshao.learning.utils.api.IBaseResultCode;
import com.laravelshao.learning.utils.constant.ResultCodeConstant;
import org.springframework.util.ObjectUtils;

/**
 * 基础响应码
 *
 * @author qinghua.shao
 * @date 2019/12/13
 * @since 1.0.0
 */
public enum BaseResultCode implements IBaseResultCode {

    /*** 成功 */
    SUCCESS(ResultCodeConstant.SUCCESS, "成功", "成功"),

    /*** 系统错误 */
    SYSTEM_ERROR(ResultCodeConstant.SYSTEM_ERROR, "系统错误", "系统开小差了~"),

    /*** 未知错误 */
    UNKNOWN_ERROR(ResultCodeConstant.UNKNOWN_ERROR, "未知错误", "发生了未知错误~");

    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 返回信息
     */
    private String returnMsg;

    BaseResultCode(String errorCode, String errorMsg, String returnMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.returnMsg = returnMsg;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public static IBaseResultCode byCode(String errorCode) {
        if (ObjectUtils.isEmpty(errorCode)) {
            return null;
        }

        for (IBaseResultCode e : values()) {
            if (e.getErrorCode().equals(errorCode)) {
                return e;
            }
        }
        return null;
    }

}