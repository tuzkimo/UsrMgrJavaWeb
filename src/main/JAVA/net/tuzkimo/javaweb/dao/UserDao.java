package net.tuzkimo.javaweb.dao;

import net.tuzkimo.javaweb.entity.User;

import java.util.List;

/**
 * 用户 DAO 接口
 * Created by tuzkimo on 2017-03-23.
 */
public interface UserDao {

    List<User> getAllUsers();

    List<User> getUsersPaper(int skip, int size);

    Integer getUsersCount();

    User getUserById(int id);

    boolean addUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(int id);

}
