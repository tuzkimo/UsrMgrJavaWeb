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
 * 删除复数用户
 * Created by tuzkimo on 2017-04-27.
 */
public class DeleteUsersServlet extends HttpServlet {

    private static final long serialVersionUID = -246612071943059705L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl(new UserDaoImpl());

        String[] idsStr = request.getParameterValues("id");
        int[] ids = new int[idsStr.length];
        for (int i = 0; i < idsStr.length; i++) {
            int id = Integer.parseInt(idsStr[i]);
            ids[i] = id;
        }

        String pageNo = request.getParameter("pageNo");

        if (userService.deleteUsers(ids)) {
            response.sendRedirect("/index?pageNo=" + pageNo);
        }
    }

}
