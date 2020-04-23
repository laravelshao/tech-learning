package com.laravelshao.learning.jdk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author qinghua.shao
 * @date 2019/10/14
 * @since 1.0.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Coupon {

    /**
     * 券来源 1-到家券 2-微信券 3-扫码购券
     */
    private Integer sourceType;

    /**
     * 券ID
     */
    private String couponId;
}
