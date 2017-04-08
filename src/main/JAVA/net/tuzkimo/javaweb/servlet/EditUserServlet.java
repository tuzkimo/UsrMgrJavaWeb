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
public class EditUserServlet extends HttpServlet {

    private static final long serialVersionUID = -6202208509979927123L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        UserService userService = new UserServiceImpl(new UserDaoImpl());
        User user = userService.getUserById(id);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/WEB-INF/views/editUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl(new UserDaoImpl());

        int id = Integer.parseInt(request.getParameter("id"));

        User user = userService.getUserById(id);

        user.setName(request.getParameter("name"));
        user.setPassword(request.getParameter("password"));
        user.setDescription(request.getParameter("description"));

        if (userService.editUser(user)) {
            System.out.println("Edited user: " + user);
            response.sendRedirect("/index");
        } else {
            System.out.println("Edited user: failed");
            request.getRequestDispatcher("/WEB-INF/views/editUser.jsp").forward(request, response);
        }

    }

}
