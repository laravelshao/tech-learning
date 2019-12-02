package com.laravelshao.learning.utils.utils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * Object增强工具类
 *
 * @author qinghua.shao
 * @date 2019/9/2
 * @since 1.0.0
 */
public class ObjectEnhanceUtils {

    /**
     * 判断对象是否为空(包含null/空数组/空集合/空字符串/零值)
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }

        if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        }
        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        }
        if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        }
        if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }
        if (obj instanceof String) {
            return "".equals(obj);
        }
        if (obj instanceof Long) {
            return 0L == ((Long) obj).longValue();
        }
        if (obj instanceof Integer) {
            return 0 == ((Integer) obj).intValue();
        }

        // else
        return false;
    }

}
