package com.wzj.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类型转换工具类
 */
public class CastUtil {
    private static final Logger logger = LoggerFactory.getLogger(CastUtil.class);

    private CastUtil() {
    }

    public static String castString(Object obj) {
        return castString(obj, "");
    }

    public static String castString(Object obj, String defaultValue) {
        return obj == null ? defaultValue : String.valueOf(defaultValue);
    }

    public static int castInt(Object obj) {
        return castInt(obj, 0);
    }

    public static int castInt(Object obj, int defaultValue) {
        int value = defaultValue;
        if (obj != null) {
            String strValue = castString(obj);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    value = Integer.parseInt(strValue);
                } catch (Exception e) {
                    value = defaultValue;
                }
            }
        }
        return value;
    }

    public static long castLong(Object obj) {
        return castLong(obj, 0L);
    }

    public static long castLong(Object obj, long defaultValue) {
        long value = defaultValue;
        if (obj != null) {
            String strValue = castString(obj);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    value = Long.parseLong(strValue);
                } catch (Exception e) {
                    value = defaultValue;
                }
            }
        }
        return value;
    }

    public static double castDouble(Object obj) {
        return castDouble(obj, 0);
    }

    public static double castDouble(Object obj, double defaultValue) {
        double value = defaultValue;
        if (obj != null) {
            String strValue = castString(obj);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    value = Double.parseDouble(strValue);
                } catch (Exception e) {
                    value = defaultValue;
                }
            }
        }
        return value;
    }

    public static boolean castBoolean(Object obj) {
        return castBoolean(obj, false);
    }

    public static boolean castBoolean(Object obj, boolean defaultValue) {
        boolean value = defaultValue;
        if (obj != null) {
            String strValue = castString(obj);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    value = Boolean.parseBoolean(strValue);
                } catch (Exception e) {
                    value = defaultValue;
                }
            }
        }
        return value;
    }
}
