package com.laravelshao.learning.utils.number;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author qinghua.shao
 * @date 2020/6/19
 * @since 1.0.0
 */
public class NumberTest {

    @Test
    public void decimalFormat() {
        // 设置小数格式为仅保留尾部非0小数
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        //DecimalFormat decimalFormat = new DecimalFormat("0.00");
        System.out.println(decimalFormat.format(10.00));
        System.out.println(decimalFormat.format(10.01));
        System.out.println(decimalFormat.format(10.050));
        System.out.println(decimalFormat.format(10.059));


        System.out.println(decimalFormat.format(new BigDecimal(10.00)));
        System.out.println(decimalFormat.format(new BigDecimal(10.01)));
        System.out.println(decimalFormat.format(new BigDecimal(10.050)));
        System.out.println(decimalFormat.format(new BigDecimal(10.059)));
    }

}
