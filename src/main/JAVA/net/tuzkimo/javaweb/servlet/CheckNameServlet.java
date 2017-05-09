package net.tuzkimo.javaweb.servlet;

import net.tuzkimo.javaweb.dao.UserDaoImpl;
import net.tuzkimo.javaweb.service.UserService;
import net.tuzkimo.javaweb.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 配合 AJAX 检查表达名称项是否重名
 * Created by tuzkimo on 2017-05-09.
 */
public class CheckNameServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserServiceImpl(new UserDaoImpl());
        resp.setContentType("text/html;charset=utf8");
        PrintWriter out = resp.getWriter();
        String name = req.getParameter("name");
        if (userService.getUserByName(name).getId() != 0) {
            String msg = "用户已存在";
            out.print(msg);
        }
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
