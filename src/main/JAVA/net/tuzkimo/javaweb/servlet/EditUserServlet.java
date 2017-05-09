package net.tuzkimo.javaweb.servlet;

import net.tuzkimo.javaweb.dao.UserDaoImpl;
import net.tuzkimo.javaweb.entity.User;
import net.tuzkimo.javaweb.service.UserService;
import net.tuzkimo.javaweb.service.UserServiceImpl;
import net.tuzkimo.javaweb.util.FormValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String description = request.getParameter("description");

        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("name", name);
        fieldValues.put("password", password);
        fieldValues.put("description", description);

        Map<String, List<String>> fieldErrors = FormValidator.getInstance().validate(fieldValues);

        if (fieldErrors.size() > 0) {
            request.setAttribute("fieldErrors", fieldErrors);
            request.getRequestDispatcher("/WEB-INF/views/addUser.jsp").forward(request, response);
        } else {
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

}
