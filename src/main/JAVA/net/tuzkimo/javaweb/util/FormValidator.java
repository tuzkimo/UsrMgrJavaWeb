package net.tuzkimo.javaweb.util;

import net.tuzkimo.javaweb.dao.UserDaoImpl;
import net.tuzkimo.javaweb.service.UserService;
import net.tuzkimo.javaweb.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 表单验证工具
 * Created by tuzkimo on 2017-05-06.
 */
public class FormValidator {

    private static FormValidator formValidator;
    private UserService userService;

    private FormValidator() {
        userService = new UserServiceImpl(new UserDaoImpl());
    }

    public static FormValidator getInstance() {
        if (formValidator == null) {
            formValidator = new FormValidator();
        }
        return formValidator;
    }

    public Map<String, List<String>> validate(Map<String, String> fields) {
        Map<String, List<String>> fieldErrors;
        fieldErrors = new HashMap<String, List<String>>();

        for (String key : fields.keySet()) {
            /*
             * 验证名称表单项
             */
            if (key.equals("name")) {
                List<String> errors = new ArrayList<String>();
                String fieldValue = fields.get(key);
                Integer fieldLength = 0;

                if (fieldValue == null || fieldValue.equals("")) {
                    errors.add("名称不能为空");
                } else if (!Pattern.matches("^[\\w\\u4e00-\\u9fa5\\s]+$", fieldValue)) {
                    errors.add("名称只能包含中英文字符");
                } else {
                    fieldLength = fieldValue.length();
                }

                if (fieldLength > 25) {
                    errors.add("名称不能超过 25 个字符");
                }

                if (userService.getUserByName(fieldValue).getId() != 0) {
                    errors.add("用户已存在");
                }

                fieldErrors.put(key, errors);
            }
            /*
             * 验证密码表单项
             */
            if (key.equals("password")) {
                List<String> errors = new ArrayList<String>();
                String fieldValue = fields.get(key);
                Integer fieldLength = 0;

                if (fieldValue == null || fieldValue.equals("")) {
                    errors.add("密码不能为空");
                } else {
                    fieldLength = fieldValue.length();
                }

                if (fieldLength < 6 || fieldLength > 20)  {
                    errors.add("密码不能小于 6 位或大于 20 位");
                }

                fieldErrors.put(key, errors);
            }
            /*
             * 验证描述表单项
             */
            if (key.equals("description")) {
                List<String> errors = new ArrayList<String>();
                String fieldValue = fields.get(key);

                if (Pattern.matches("^[<>&#]+$", fieldValue)) {
                    errors.add("描述不能包含 < > & # 等非法字符");
                }

                fieldErrors.put(key, errors);
            }
        }

        return fieldErrors;
    }

}
