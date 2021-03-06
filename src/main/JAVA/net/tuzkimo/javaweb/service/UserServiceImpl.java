package net.tuzkimo.javaweb.service;

import net.tuzkimo.javaweb.dao.UserDao;
import net.tuzkimo.javaweb.entity.User;

import java.util.List;

/**
 * 用户服务实现类
 * Created by tuzkimo on 2017-03-23.
 */
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public List<User> getUsersPaper(int skip, int size) {
        return userDao.getUsersPaper(skip, size);
    }

    public Integer getUsersCount() {
        return userDao.getUsersCount();
    }

    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    public boolean addUser(User user) {
        return userDao.addUser(user);
    }

    public boolean editUser(User user) {
        return userDao.updateUser(user);
    }

    public boolean deleteUser(int id) {
        return userDao.deleteUser(id);
    }

    public boolean deleteUsers(int[] ids) {
        for (int id : ids) {
            if (!userDao.deleteUser(id)) {
                return false;
            }
        }
        return true;
    }

    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }
}
