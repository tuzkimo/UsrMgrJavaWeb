package net.tuzkimo.javaweb.dao;

import java.sql.*;

/**
 * Created by tuzkimo on 2017-03-23.
 */
class BaseDao {

    private static Connection connection;
    private static PreparedStatement statement;
    private static ResultSet resultSet;

    // 数据源参数
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/javaweb";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "p123456";

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
