package net.tuzkimo.javaweb.servlet;

import net.tuzkimo.javaweb.dao.UserDaoImpl;
import net.tuzkimo.javaweb.service.UserService;
import net.tuzkimo.javaweb.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 列出所有用户
 * Created by tuzkimo on 2017-03-23.
 */
public class ListUsersServlet extends HttpServlet {

    private static final long serialVersionUID = -5654045353166209211L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl(new UserDaoImpl());
        request.setAttribute("users", userService.getAllUsers());
        request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
    }

}
