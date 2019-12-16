package com.laravelshao.learning.utils.expection;


import com.laravelshao.learning.utils.api.IBaseResultCode;
import com.laravelshao.learning.utils.api.Result;
import lombok.Data;

/**
 * 业务异常类
 *
 * @author qinghua.shao
 * @date 2019/12/13
 * @since 1.0.0
 */
@Data
public class BizException extends RuntimeException implements IBaseResultCode {

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

    public BizException(String errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }

    public BizException(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BizException(IBaseResultCode iBaseResultCode) {
        super(iBaseResultCode.getErrorMsg());
        this.errorCode = iBaseResultCode.getErrorCode();
        this.errorMsg = iBaseResultCode.getErrorMsg();
    }

    public BizException(IBaseResultCode iBaseResultCode, Throwable cause) {
        super(cause);
        this.errorCode = iBaseResultCode.getErrorCode();
        this.errorMsg = iBaseResultCode.getErrorMsg();
    }

    public BizException(IBaseResultCode iBaseResultCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = iBaseResultCode.getErrorCode();
        this.errorMsg = errorMsg;
    }

    public BizException(String errorCode, String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BizException(String errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public BizException(String errorCode, String errorMsg, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(errorMsg, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Result getResult() {
        return Result.fail(errorCode, this.getErrorMsg() != null ? this.getErrorMsg() : "something wrong!");
    }

}
