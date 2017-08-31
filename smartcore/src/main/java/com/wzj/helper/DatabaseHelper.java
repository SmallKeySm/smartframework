package com.wzj.helper;

import com.wzj.util.PropUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class DatabaseHelper {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseHelper.class);

    private static final QueryRunner QUERY_RUNNER = new QueryRunner();

    private static final ThreadLocal<Connection> CONNECTION_THREAD_LOCAL = new ThreadLocal<>();

    private static final String DRIVER;

    private static final String URL;

    private static final String PASSWORD;

    private static final String USER_NAME;

    static {
        Properties properties = PropUtil.loadProps("config.properties");
        DRIVER = properties.getProperty("jdbc.driver");
        URL = properties.getProperty("jdbc.url");
        PASSWORD = properties.getProperty("jdbc.password");
        USER_NAME = properties.getProperty("jdbc.userName");

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            logger.error("加载驱动类错误", e);
        }
    }

    public static Connection getConnection() {
        Connection connection = CONNECTION_THREAD_LOCAL.get();
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            } catch (SQLException e) {
                logger.error("获取数据库连接失败", e);
            } finally {
                CONNECTION_THREAD_LOCAL.set(connection);
            }
        }
        return connection;
    }

    public static void closeConnection() {
        Connection connection = CONNECTION_THREAD_LOCAL.get();
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("关闭数据库连接错误", e);
            }
        }
    }

    /**
     * 查询实体列表
     *
     * @param entityClass
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public static <T> List<T> getEntityList(Class<T> entityClass, String sql, Object... params) {
        List<T> entityList = null;
        try {
            entityList = QUERY_RUNNER.query(getConnection(), sql, new BeanListHandler<T>(entityClass), params);
        } catch (SQLException e) {
            logger.error("查询出错", e);
        } finally {
            closeConnection();
        }
        return entityList;
    }

    /**
     * 查询实体
     *
     * @param entityClass
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public static <T> T getEntity(Class<T> entityClass, String sql, Object... params) {
        T entity = null;
        try {
            entity = QUERY_RUNNER.query(getConnection(), sql, new BeanHandler<>(entityClass), params);
        } catch (SQLException e) {
            logger.error("查询实体出错", e);
        } finally {
            closeConnection();
        }
        return entity;
    }
}
