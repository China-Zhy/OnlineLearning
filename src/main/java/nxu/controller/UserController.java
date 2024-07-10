package nxu.controller;

import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nxu.entity.Role;
import nxu.entity.User;
import nxu.service.RoleService;
import nxu.service.RoleServiceImpl;
import nxu.service.UserService;
import nxu.service.UserServiceImpl;
import nxu.utils.BaseServlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * 用户相关功能的控制器 (张宏业)
 */
@WebServlet("/user/*")
public class UserController extends BaseServlet {

    private static final UserService userService = new UserServiceImpl();

    private static final RoleService roleService = new RoleServiceImpl();

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

    // 删除用户
    public void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int result = userService.deleteUserById(Integer.parseInt(req.getParameter("id")));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        String info = result > 0 ? "删除成功" : "删除失败";
        jsonObject.put("info", info);
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

    // 查询全部用户
    public void queryAllUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HashMap<String, Object> map = new HashMap<>();
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String gender = req.getParameter("gender");
        String type = req.getParameter("type");

        System.out.println("name=" + name);
        System.out.println("phone=" + phone);
        System.out.println("gender=" + gender);
        System.out.println("type=" + type);

        if (name != null && !name.isEmpty()) {
            map.put("name", name);
        }
        if (phone != null && !phone.isEmpty()) {
            map.put("phone", phone);
        }
        if (gender != null && !gender.isEmpty()) {
            if (Integer.parseInt(gender) != 0) {
                map.put("gender", Integer.parseInt(gender));
            }
        }
        if (type != null && !type.isEmpty()) {
            if (Integer.parseInt(type) != 0) {
                map.put("type", Integer.parseInt(type));
            }
        }

        List<User> userList = userService.queryAllUsers(map);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "success");
        jsonObject.put("count", userList.size());
        jsonObject.put("data", userList);

        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

    // 获取用户角色类型
    public void queryAllRole(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Role> allRoles = roleService.getAllRoles(1, 5);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list", allRoles);
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