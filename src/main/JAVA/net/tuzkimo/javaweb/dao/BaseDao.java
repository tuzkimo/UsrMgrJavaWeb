package net.tuzkimo.javaweb.dao;

import net.tuzkimo.javaweb.util.ConfigManager;

import java.sql.*;

/**
 * 基础 DAO
 * Created by tuzkimo on 2017-03-23.
 */
class BaseDao {

    private static Connection connection;
    private static PreparedStatement statement;
    private static ResultSet resultSet;

    // 数据源参数
    private static final String JDBC_DRIVER = ConfigManager.getInstance().getString("jdbc.driver");
    private static final String JDBC_URL = ConfigManager.getInstance().getString("jdbc.url");
    private static final String JDBC_USERNAME = ConfigManager.getInstance().getString("jdbc.username");
    private static final String JDBC_PASSWORD = ConfigManager.getInstance().getString("jdbc.password");

    private boolean getConnection() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    ResultSet executeQuery(String sql, Object[] params) {
        if (getConnection()) {
            try {
                statement = connection.prepareStatement(sql);
                for (int i = 0; i < params.length; i++) {
                    statement.setObject(i + 1, params[i]);
                }
                resultSet = statement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultSet;
    }

    int executeUpdate(String sql, Object[] params) {
        int effectRows = 0;
        if (getConnection()) {
            try {
                statement = connection.prepareStatement(sql);
                for (int i = 0; i < params.length; i++) {
                    statement.setObject(i + 1, params[i]);
                }
                effectRows = statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return effectRows;
    }

    @SuppressWarnings("Duplicates")
    boolean close() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

}
