package net.tuzkimo.javaweb.servlet;

import net.tuzkimo.javaweb.dao.UserDaoImpl;
import net.tuzkimo.javaweb.entity.User;
import net.tuzkimo.javaweb.service.UserService;
import net.tuzkimo.javaweb.service.UserServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 增加用户
 * Created by tuzkimo on 2017-03-24.
 */
public class UpPhotoServlet extends HttpServlet {

    private static final long serialVersionUID = -6202208509979927123L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        UserService userService = new UserServiceImpl(new UserDaoImpl());
        User user = userService.getUserById(id);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/WEB-INF/views/upPhoto.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl(new UserDaoImpl());

        User user = null;

        // 判断是否为二进制内容
        if (ServletFileUpload.isMultipartContent(request)) {

            // 创建文件上传核心类
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);

            try {

                // 解释表单项目
                List<FileItem> fileItems = servletFileUpload.parseRequest(request);

                // 遍历表单项目
                for (FileItem fileItem : fileItems) {

                    // 判断是否为文件项
                    if (fileItem.isFormField()) {

                        // 判断是否为编号表单项
                        if (fileItem.getFieldName().equals("id")) {

                            // 获取目标用户
                            int id = Integer.parseInt(fileItem.getString("utf-8"));
                            user = userService.getUserById(id);

                        }

                    } else {

                        // 文件非空验证
                        if (fileItem.getSize() <= 0) {
                            request.setAttribute("message", "Please upload a photo.");
                            request.setAttribute("user", user);
                            request.getRequestDispatcher("/WEB-INF/views/upPhoto.jsp").forward(request, response);
                        }

                        // 获取文件名
                        String photoName = fileItem.getName();

                        // 文件类型过滤
                        if (!(photoName.endsWith(".jpg") || photoName.endsWith(".png"))) {
                            request.setAttribute("message", "Sorry, we only accept jpg or png file.");
                            request.setAttribute("user", user);
                            request.getRequestDispatcher("/WEB-INF/views/upPhoto.jsp").forward(request, response);
                        }

                        // 获取文件储存路径
                        String savePath = request.getSession().getServletContext().getRealPath("/photos");

                        // 创建本地保存文件
                        File savePhoto = new File(savePath, photoName);

                        // 如目标路径不存在创建目录
                        if (!savePhoto.getParentFile().exists()) {
                            savePhoto.getParentFile().mkdir();
                        }

                        // 上传文件内容写入本地保存文件
                        fileItem.write(savePhoto);

                        // 跟新数据库记录
                        if (user != null) {
                            user.setPhoto(photoName);
                        }

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (userService.editUser(user)) {
            System.out.println("Uploaded photo: " + user.getPhoto());
            response.sendRedirect("/index");
        } else {
            System.out.println("Uploaded photo: failed");
            request.getRequestDispatcher("/WEB-INF/views/upPhoto.jsp").forward(request, response);
        }

    }

}
