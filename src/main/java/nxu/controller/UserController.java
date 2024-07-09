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
public class UserController extends BaseServlet {

    private static final UserService userService = new UserServiceImpl();

    // 用户登录
    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        String vercode = req.getParameter("vercode");
        System.out.println("login收到的参数：" + account + " " + password + " " + vercode);
        User user = userService.queryUserToLogin(account, password);

        JSONObject jsonObject = new JSONObject();

        if (user != null) {
            req.getSession().setAttribute("user", user);
            jsonObject.put("code", 0);
            jsonObject.put("result", true);
            jsonObject.put("msg", "登录成功，欢迎您：" + user.getName());
        } else {
            jsonObject.put("code", 0);
            jsonObject.put("result", false);
            jsonObject.put("msg", "登录失败，账号或密码错误");
        }

        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

    // 用户注册
    public void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("进入注册的controller");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "注册这块的服务没写");
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

    // 查询全部用户
    public void queryAllUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<User> userList = userService.queryAllUsers();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "success");
        jsonObject.put("count", userList.size());
        jsonObject.put("data", userList);
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

    // 短信验证码
    public void sendCode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("进入发送验证码的controller");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "验证码获取成功：UserController");
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }
}