package net.tuzkimo.javaweb.dao;

import net.tuzkimo.javaweb.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户 DAO 实现类
 * Created by tuzkimo on 2017-03-23.
 */
public class UserDaoImpl extends BaseDao implements UserDao {

    private static final String COLUMNS = "id, name, password, description, photo";

    private static User queryUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        while (resultSet.next()) {
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setPassword(resultSet.getString("password"));
            user.setDescription(resultSet.getString("description"));
            user.setPhoto(resultSet.getString("photo"));
        }
        return user;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        try {
            String sql = "SELECT * FROM user";
            Object[] params = {};
            ResultSet resultSet = executeQuery(sql, params);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setDescription(resultSet.getString("description"));
                user.setPhoto(resultSet.getString("photo"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return users;
    }

    public List<User> getUsersPaper(int skip, int size) {
        List<User> users = new ArrayList<User>();
        try {
            String sql = "SELECT * FROM user LIMIT ?, ?";
            Object[] params = {skip, size};
            ResultSet resultSet = executeQuery(sql, params);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword((resultSet.getString("password")));
                user.setDescription(resultSet.getString("description"));
                user.setPhoto(resultSet.getString("photo"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return users;
    }

    public Integer getUsersCount() {
        Integer usersCount = 0;
        try {
            String sql = "SELECT COUNT(id) FROM user";
            Object[] params = {};
            ResultSet resultSet = executeQuery(sql, params);
            if (resultSet.next()) {
                usersCount = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return usersCount;
    }

    public User getUserById(int id) {
        User user = null;
        try {
            String sql = "SELECT * FROM user WHERE id = ?";
            Object[] params = {id};
            ResultSet resultSet = executeQuery(sql, params);
            user = queryUser(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return user;
    }

    public boolean addUser(User user) {
        boolean result = false;
        try {
            String sql = "INSERT INTO user (name, password, description) VALUES (?, ?, ?)";
            Object[] params = {user.getName(), user.getPassword(), user.getDescription()};
            int effectRows = executeUpdate(sql, params);
            if (effectRows > 0) {
                result = true;
            }
        } finally {
            close();
        }
        return result;
    }

    public boolean updateUser(User user) {
        boolean result = false;
        try {
            String sql = "UPDATE user SET name = ?, password = ?, description = ?, photo = ? WHERE id = ?";
            Object[] params = {user.getName(), user.getPassword(), user.getDescription(), user.getPhoto(), user.getId()};
            int effectRows = executeUpdate(sql, params);
            if (effectRows > 0) {
                result = true;
            }
        } finally {
            close();
        }
        return result;
    }

    public boolean deleteUser(int id) {
        boolean result = false;
        try {
            String sql = "DELETE FROM user WHERE id = ?";
            Object[] params = {id};
            int effectRows = executeUpdate(sql, params);
            if (effectRows > 0) {
                result = true;
            }
        } finally {
            close();
        }
        return result;
    }

    public User getUserByName(String name) {
        User user = null;
        try {
            String sql = "SELECT " + COLUMNS + " FROM user WHERE name = ?";
            Object[] params = {name};
            ResultSet resultSet = executeQuery(sql, params);
            user = queryUser(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
