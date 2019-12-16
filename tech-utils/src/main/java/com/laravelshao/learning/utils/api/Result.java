package com.laravelshao.learning.utils.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.laravelshao.learning.utils.constant.ResultCodeConstant;
import com.laravelshao.learning.utils.enums.BaseResultCode;
import com.laravelshao.learning.utils.expection.BizException;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 返回通用类
 *
 * @author qinghua.shao
 * @date 2019/12/13
 * @since 1.0.0
 */
@Data
public class Result<T> implements Serializable {

    /**
     * 错误码
     */
    public String errorCode;

    /**
     * 错误信息
     */
    public String errorMsg;

    /**
     * 返回信息
     */
    public String returnMsg;

    /**
     * 返回结果对象
     */
    public T data;

    /**
     * 异常处理堆栈
     */
    @JsonIgnore
    protected transient Throwable exception;

    public Result() {
        this(BaseResultCode.SUCCESS);
    }

    protected Result(T data) {
        this();
        this.data = data;
    }

    protected Result(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    protected Result(String errorCode, String errorMsg, T data) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.data = data;
    }

    protected Result(IBaseResultCode iBaseResultCode) {
        this.errorCode = iBaseResultCode.getErrorCode();
        this.errorMsg = iBaseResultCode.getErrorMsg();
        this.returnMsg = iBaseResultCode.getReturnMsg();
    }

    protected Result(IBaseResultCode iBaseResultCode, Throwable exception) {
        this.errorCode = iBaseResultCode.getErrorCode();
        this.errorMsg = iBaseResultCode.getErrorMsg();
        this.exception = exception;
    }

    protected Result(String errorCode, String errorMsg, Throwable exception) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.exception = exception;
    }

    public static <T> Result<T> ok() {
        return new Result(BaseResultCode.SUCCESS);
    }

    public static <T> Result<T> ok(T t) {
        return new Result(t);
    }

    public static <T> Result<T> okMsg(String errorMsg) {
        return new Result(ResultCodeConstant.SUCCESS, errorMsg);
    }

    public static <T> Result<T> okMsg(String errorMsg, T t) {
        return new Result(ResultCodeConstant.SUCCESS, errorMsg, t);
    }

    public static <K, V> MapResult<K, V> okMap(Map<K, V> data) {
        return new MapResult(data);
    }

    public static <T> Result<T> okList(List<T> data) {
        return new ListResult(data);
    }

    public static <T> Result<T> fail(String errorCode, String errorMsg) {
        return new ErrorResult<>(errorCode, errorMsg);
    }

    public static <T> Result<T> fail(String errorMsg) {
        return new ErrorResult<>("-1", errorMsg);
    }

    public static <T> Result<T> fail(IBaseResultCode baseResultEnum) {
        return new ErrorResult<>(baseResultEnum);
    }

    public static <T> Result<T> fail(IBaseResultCode baseResultEnum, String errorMsg) {
        return new ErrorResult<>(baseResultEnum, errorMsg);
    }

    public static <T> Result<T> unsureReturn(String errorCode, String errorMsg, T data) {
        return new Result<T>(errorCode, errorMsg, data);
    }

    public static <T> Result<T> fail(IBaseResultCode baseResultEnum, Throwable exception) {
        return new ErrorResult<>(baseResultEnum, exception);
    }

    public static <T> Result<T> fail(IBaseResultCode baseResultEnum, Throwable e, String errorMsg) {
        Result result = new ErrorResult<>(baseResultEnum, e);
        result.errorMsg = errorMsg;
        return result;
    }

    public boolean success() {
        return ResultCodeConstant.SUCCESS.equals(errorCode);
    }

    public <R> Result<R> ifSuccess(Function<T, ? extends Result<R>> function) {
        if (success()) {
            return function.apply(data);
        }
        return Result.fail(BaseResultCode.SYSTEM_ERROR);
    }

    public Result<T> ifNotSuccess(Consumer<? super T> consumer) {
        if (!success()) {
            consumer.accept(data);
        }
        return this;
    }

    public Result<T> ifNotSuccessThrowException() {
        if (!success()) {
            throw new BizException(this.errorCode, this.errorMsg);
        }
        return this;
    }

    public Result<T> ifNotSuccessOrNullThrowException() {
        if (!success() || this.data == null) {
            throw new BizException(this.errorCode, this.errorMsg);
        }
        return this;
    }

    public Result<T> ifNotSuccessThrowException(BizException biz) {
        if (!success()) {
            throw biz;
        }
        return this;
    }
}