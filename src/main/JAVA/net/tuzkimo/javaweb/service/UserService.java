package net.tuzkimo.javaweb.service;

import net.tuzkimo.javaweb.entity.User;

import java.util.List;

/**
 * 用户服务接口
 * Created by tuzkimo on 2017-03-23.
 */
public interface UserService {

    List<User> getAllUsers();

    List<User> getUsersPaper(int skip, int size);

    Integer getUsersCount();

    User getUserById(int id);

    boolean addUser(User user);

    boolean editUser(User user);

    boolean deleteUser(int id);

    boolean deleteUsers(int[] ids);

}
