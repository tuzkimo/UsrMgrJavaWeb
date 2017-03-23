package net.tuzkimo.javaweb.service;

import net.tuzkimo.javaweb.entity.User;

import java.util.List;

/**
 * Created by tuzkimo on 2017-03-23.
 */
public interface UserService {

    List<User> getAllUsers();

    User getUserById(int id);

}
