package net.tuzkimo.javaweb.test;

import net.tuzkimo.javaweb.dao.UserDao;
import net.tuzkimo.javaweb.dao.UserDaoImpl;
import net.tuzkimo.javaweb.entity.User;
import net.tuzkimo.javaweb.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by tuzkimo on 2017-03-23.
 */
public class UserTest {

    private UserServiceImpl userService;

    @Before
    public void before() {
        UserDao userDao = new UserDaoImpl();
        userService = new UserServiceImpl();
        userService.setUserDao(userDao);
    }

    @Test
    public void getAllUsersTest() {
        List<User> users = userService.getAllUsers();
        System.out.println(users);
    }

    @Test
    public void getUserByIdTest() {
        User user = userService.getUserById(1);
        System.out.println(user);
    }

}
