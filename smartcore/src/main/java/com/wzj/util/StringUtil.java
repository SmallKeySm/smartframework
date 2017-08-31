package com.wzj.util;

import org.apache.commons.lang3.StringUtils;

/**
 * String 工具类
 */
public class StringUtil {

    private StringUtil() {
    }

    public static boolean isEmpty(String str) {
        if (str != null) {
            str = str.trim();
        }
        return StringUtils.isEmpty(str);
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}
