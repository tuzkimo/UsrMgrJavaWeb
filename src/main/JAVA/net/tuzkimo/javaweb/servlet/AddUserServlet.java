package net.tuzkimo.javaweb.servlet;

import net.tuzkimo.javaweb.dao.UserDaoImpl;
import net.tuzkimo.javaweb.entity.User;
import net.tuzkimo.javaweb.service.UserService;
import net.tuzkimo.javaweb.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 增加用户
 * Created by tuzkimo on 2017-03-24.
 */
public class AddUserServlet extends HttpServlet {

    private static final long serialVersionUID = -6202208509979927123L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/addUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl(new UserDaoImpl());

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String description = request.getParameter("description");

        User user = new User(name, password, description);

        if (userService.addUser(user)) {
            System.out.println("Added user: " + user);
            response.sendRedirect("/index");
        } else {
            System.out.println("Added user: failed");
            request.getRequestDispatcher("/WEB-INF/views/addUser.jsp").forward(request, response);
        }

    }

}
