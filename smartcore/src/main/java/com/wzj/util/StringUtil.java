package com.wzj.util;

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
        return StringUtil.isEmpty(str);
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}
