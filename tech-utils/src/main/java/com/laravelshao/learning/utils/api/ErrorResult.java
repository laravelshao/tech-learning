package com.laravelshao.learning.utils.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 错误结果类
 *
 * @author qinghua.shao
 * @date 2019/12/13
 * @since 1.0.0
 */
@Getter
@Setter
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResult<T> extends Result<T> {

    protected ErrorResult() {
    }

    protected ErrorResult(String errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    protected ErrorResult(IBaseResultCode baseResultCode) {
        super(baseResultCode);
    }

    protected ErrorResult(IBaseResultCode baseResultCode, String errorMsg) {
        super(baseResultCode);
        this.errorMsg = errorMsg;
    }

    protected ErrorResult(IBaseResultCode baseResultCode, Throwable throwable) {
        super(baseResultCode, throwable);
    }

    protected ErrorResult(String errorCode, String errorMsg, T data) {
        super(errorCode, errorMsg, data);
    }
}
