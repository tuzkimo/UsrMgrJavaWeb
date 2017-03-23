package net.tuzkimo.javaweb.dao;

import net.tuzkimo.javaweb.entity.User;

import java.util.List;

/**
 * Created by tuzkimo on 2017-03-23.
 */
public interface UserDao {

    List<User> getAllUsers();

    User getUserById(int id);

}
