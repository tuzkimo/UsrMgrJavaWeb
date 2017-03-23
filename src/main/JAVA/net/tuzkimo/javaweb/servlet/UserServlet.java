package net.tuzkimo.javaweb.servlet;

import net.tuzkimo.javaweb.dao.UserDao;
import net.tuzkimo.javaweb.dao.UserDaoImpl;
import net.tuzkimo.javaweb.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tuzkimo on 2017-03-23.
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserServiceImpl userService = new UserServiceImpl();
        UserDao userDao = new UserDaoImpl();
        userService.setUserDao(userDao);
        request.setAttribute("users", userService.getAllUsers());
        request.getRequestDispatcher("/WEB-INF/views/users.jsp").forward(request, response);
    }

}
