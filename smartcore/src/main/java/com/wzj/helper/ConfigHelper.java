package com.wzj.helper;

import com.wzj.util.PropUtil;

import java.util.Properties;

/**
 * 配置读取类
 */
public class ConfigHelper {

    /**
     * 配置文件名
     */
    private static final String CONFIG_FILE = "smart.config";
    /**
     * jdbc驱动
     */
    private static final String JDBC_DRIVER = "smart.jdbc.driver";
    /**
     * jdbc url
     */
    private static final String JDBC_URL = "smart.jdbc.url";
    /**
     * jdbc 用户名
     */
    private static final String JDBC_USERNAME = "smart.jdbc.username";
    /**
     * jdbc 密码
     */
    private static final String JDBC_PASSWORD = "smart.jdbc.password";
    /**
     * 包路径
     */
    private static final String APP_BASE_PATH = "app.base.path";
    /**
     * jsp根目录
     */
    private static final String APP_JSP_PATH = "app.jsp.path";
    /**
     * 静态资源根目录
     */
    private static final String APP_ASSERT_PATH = "app.assert.path";

    private static final Properties properties = PropUtil.loadProps(CONFIG_FILE);

    public static String getJdbcDriver() {
        return PropUtil.getString(properties, JDBC_DRIVER);
    }

    public static String getJdbcUrl() {
        return PropUtil.getString(properties, JDBC_URL);
    }

    public static String getJdbcUsername() {
        return PropUtil.getString(properties, JDBC_USERNAME);
    }

    public static String getJdbcPassword() {
        return PropUtil.getString(properties, JDBC_PASSWORD);
    }

    public static String getAppBasePath() {
        return PropUtil.getString(properties, APP_BASE_PATH);
    }

    public static String getAppJspPath() {
        return PropUtil.getString(properties, APP_JSP_PATH, "/WEB-INF/jsp/");
    }

    public static String getAppAssertPath() {
        return PropUtil.getString(properties, APP_ASSERT_PATH, "/assert/");
    }
}
