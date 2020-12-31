package com.laravelshao.learning.utils.string;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @author qinghua.shao
 * @date 2019/8/21
 * @since 1.0.0
 */
public class StringTest {

    /**
     * 截取指定字符串之间的字符串
     */
    @Test
    public void substringBetween() {
        System.out.println(StringUtils.substringBetween("tag456tag", "tag"));
    }
}
