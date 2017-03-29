package net.tuzkimo.javaweb.test;

import net.tuzkimo.javaweb.dao.UserDaoImpl;
import net.tuzkimo.javaweb.entity.User;
import net.tuzkimo.javaweb.service.UserService;
import net.tuzkimo.javaweb.service.UserServiceImpl;
import org.junit.Test;

import java.util.List;

/**
 * 用户测试类
 * Created by tuzkimo on 2017-03-23.
 */
public class UserTest {

    private final UserService userService;

    public UserTest() {
        userService = new UserServiceImpl(new UserDaoImpl());
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

    @Test
    public void addUserTest() throws Exception {
        User user = new User("Steve Curry", "sc123456", "PG");
        boolean result = userService.addUser(user);
        System.out.println("Added user? " + result);
    }

    @Test
    public void editUserTest() throws Exception {
        User user = new User("Steve Curry", "sc123456", "PF");
        user.setId(6);
        boolean result = userService.editUser(user);
        System.out.println("Updated user? " + result);
    }

    @Test
    public void deleteUserTest() throws Exception {
        boolean result = userService.deleteUser(6);
        System.out.println("Deleted user? " +result);
    }
    
}
