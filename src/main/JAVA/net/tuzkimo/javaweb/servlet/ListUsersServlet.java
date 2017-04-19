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

        int size = 5;

        int usersCount = userService.getUsersCount();

        int pages;
        if (usersCount % size == 0) {
            pages = usersCount / size;
        } else {
            pages = usersCount / size + 1;
        }

        Integer pageNo = 1;
        if (request.getParameter("pageNo") != null) {
            pageNo = Integer.parseInt(request.getParameter("pageNo"));
        }
        if (pageNo < 1) {
            pageNo = 1;
        }
        if (pageNo > pages) {
            pageNo = pages;
        }

        int skip = (pageNo - 1) * 5;
        request.setAttribute("users", userService.getUsersPaper(skip, size));
        request.setAttribute("pageNo", pageNo);
        request.setAttribute("pages", pages);
        request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
    }

}
