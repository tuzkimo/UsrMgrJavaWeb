package net.tuzkimo.javaweb.dao;

import net.tuzkimo.javaweb.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tuzkimo on 2017-03-23.
 */
public class UserDaoImpl extends BaseDao implements UserDao {

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        String sql = "SELECT * FROM user";
        Object[] params = {};
        ResultSet resultSet = executeQuery(sql, params);
        try {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setDescription(resultSet.getString("description"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return users;
    }

    public User getUserById(int id) {
        User user = new User();
        String sql = "SELECT * FROM user WHERE id = ?";
        Object[] params = {id};
        ResultSet resultSet = executeQuery(sql, params);
        try {
            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setDescription(resultSet.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return user;
    }

}
