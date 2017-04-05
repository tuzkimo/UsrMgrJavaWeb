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
public class AddUserServlet extends HttpServlet {

    private static final long serialVersionUID = -6202208509979927123L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/addUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl(new UserDaoImpl());

        User user = new User();

        String fieldName;

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        String uploadFilePath = request.getSession().getServletContext().getRealPath("/upload");

        File saveDir = new File(uploadFilePath);

        if (!saveDir.exists()) {
            //noinspection ResultOfMethodCallIgnored
            saveDir.mkdir();
        }

        if (isMultipart) {
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
            try {
                List<FileItem> fileItems = upload.parseRequest(request);
                for (FileItem fileItem : fileItems) {
                    if (fileItem.isFormField()) {
                        fieldName = fileItem.getFieldName();
                        if (fieldName.equals("name")) {
                            user.setName(fileItem.getString("utf-8"));
                        } else if (fieldName.equals("password")) {
                            user.setPassword(fileItem.getString("utf-8"));
                        } else if (fieldName.equals("description")) {
                            user.setDescription(fileItem.getString("utf-8"));
                        }
                    } else {
                        String uploadFileName = fileItem.getName();
                        if (uploadFileName != null && !uploadFileName.equals("")) {
                            File uploadFile = new File(uploadFileName);
                            File saveFile = new File(uploadFilePath, uploadFile.getName());
                            fileItem.write(saveFile);
                            user.setPhoto(uploadFile.getName());
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Uploaded success: " + user.getPhoto());

        if (userService.addUser(user)) {
            System.out.println("Added user: " + user);
            response.sendRedirect("/index");
        } else {
            System.out.println("Added user: failed");
            request.getRequestDispatcher("/WEB-INF/views/addUser.jsp").forward(request, response);
        }

    }

}
