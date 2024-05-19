package nxu.controller;

import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nxu.entity.User;
import nxu.service.UserService;
import nxu.service.UserServiceImpl;
import nxu.utils.BaseServlet;

import java.io.IOException;
import java.util.List;

/**
 * 用户相关功能的控制器 (张宏业)
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    private static final UserService userService = new UserServiceImpl();

    public void queryAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<User> userList = userService.queryAllUsers();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "success");
        jsonObject.put("count", userList.size());
        jsonObject.put("data", userList);
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }
}