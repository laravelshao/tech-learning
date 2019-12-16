package com.laravelshao.learning.utils.api;

/**
 * 基础错误码基类
 *
 * @author qinghua.shao
 * @date 2019/12/13
 * @since 1.0.0
 */
public interface IBaseResultCode {

    /**
     * 获取错误码
     *
     * @return 错误码
     */
    String getErrorCode();

    /**
     * 获取错误信息(内部)
     *
     * @return 错误信息
     */
    String getErrorMsg();

    /**
     * 获取返回信息(对外)
     *
     * @return 返回信息
     */
    String getReturnMsg();

}
