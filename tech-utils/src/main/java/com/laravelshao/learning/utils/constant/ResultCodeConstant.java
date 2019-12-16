package com.laravelshao.learning.utils.constant;

/**
 * 返回码常量
 *
 * <p>状态码规则
 * +-----+--------+-----+---------+---------+
 * | 0   | 11     | 22  | 33      | 0001    |
 * +-----+--------+-----+---------+---------+
 * | 留空 | 业务线 | 项目 | 模块服务 | 具体code |
 * +-----+--------+-----+---------+---------+
 * |     | 电商   | 营销 | 模块服务 | 具体code |
 * +-----+--------+-----+---------+---------+
 *
 * @author qinghua.shao
 * @date 2019/12/13
 * @since 1.0.0
 */
public interface ResultCodeConstant {

    String SUCCESS = "1";

    /**
     * 基础平台错误码前缀
     */
    String COMMON_CODE_PREFIX = "0010000";

    /**
     * 系统错误
     */
    String SYSTEM_ERROR = COMMON_CODE_PREFIX + "0000";

    /**
     * 未知错误
     */
    String UNKNOWN_ERROR = COMMON_CODE_PREFIX + "0001";
}
