package net.tuzkimo.javaweb.service;

import net.tuzkimo.javaweb.dao.UserDao;
import net.tuzkimo.javaweb.entity.User;

import java.util.List;

/**
 * Created by tuzkimo on 2017-03-23.
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

}
