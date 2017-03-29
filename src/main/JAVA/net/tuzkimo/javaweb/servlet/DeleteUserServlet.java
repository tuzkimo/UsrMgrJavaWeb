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
 * 删除用户 Servlet
 * Created by tuzkimo on 2017-03-29.
 */
public class DeleteUserServlet extends HttpServlet {

    private static final long serialVersionUID = -1130876067771340262L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl(new UserDaoImpl());

        int id = Integer.parseInt(request.getParameter("id"));

        if (userService.deleteUser(id)) {
            System.out.println("Deleted user with id #" + id);
            response.sendRedirect("/index");
        }

    }

}
