package nxu.controller;

import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nxu.entity.Role;
import nxu.entity.User;
import nxu.service.RoleService;
import nxu.service.impl.RoleServiceImpl;
import nxu.service.UserService;
import nxu.service.impl.UserServiceImpl;
import nxu.utils.BaseServlet;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * 用户相关功能的控制器 (张宏业)
 */
@WebServlet("/user/*")
public class UserController extends BaseServlet {

    private static final UserService userService = new UserServiceImpl();

    private static final RoleService roleService = new RoleServiceImpl();

    // 用户登录
    public void login(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);

        String account = req.getParameter("account");
        String password = req.getParameter("password");
        String vercode = req.getParameter("vercode");

        // 验证码功能
        if (!Objects.equals(vercode, "12138")) {
            jsonObject.put("result", false);
            jsonObject.put("msg", "验证码输入错误，请重试！");
            resp.setContentType("application/json;");
            resp.getWriter().write(jsonObject.toString());
            return;
        }

        User user = userService.queryUserToLogin(account, password);

        if (user != null) {
            req.getSession().setAttribute("Admin", user);
            jsonObject.put("role", user.getType());
            jsonObject.put("result", true);
            jsonObject.put("msg", "登录成功，欢迎您：" + user.getName());
        } else {
            jsonObject.put("result", false);
            jsonObject.put("msg", "登录失败，账号或密码错误");
        }
        resp.setContentType("application/json;");
        resp.getWriter().write(jsonObject.toString());
    }

    // 退出登录
    public void logout(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.getSession().removeAttribute("Admin");
        resp.sendRedirect("/views/user/userLogin.jsp");
    }

    // 用户注册
    public void register(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");
        String vercode = req.getParameter("vercode");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);

        // 验证码功能
        if (!Objects.equals(vercode, "12138")) {
            jsonObject.put("result", false);
            jsonObject.put("msg", "验证码输入错误，请重试!");
            resp.setContentType("application/json;");
            resp.getWriter().write(jsonObject.toString());
            return;
        }

        int userExist = userService.isUserExist(phone);
        if (userExist > 0) {
            jsonObject.put("result", false);
            jsonObject.put("msg", "注册失败，手机号" + phone + "已被注册!");
        } else {
            jsonObject.put("result", true);
            jsonObject.put("phone", phone);
            jsonObject.put("password", password);
            jsonObject.put("msg", "此手机号可用，请继续完善您的信息!");
        }
        resp.setContentType("application/json;");
        resp.getWriter().write(jsonObject.toString());
    }

    // 前往完善用户信息
    public void toCompleteUserInfo(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");
        req.setAttribute("phone", phone);
        req.setAttribute("password", password);
        req.getRequestDispatcher("/views/user/userInsert.jsp").forward(req, resp);
    }

    // 完善用户信息(最终注册)
    public void doCompleteUserInfo(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String image = req.getParameter("image");
        String newImage = "/layuiadmin/upload/" + image;
        int gender = Integer.parseInt(req.getParameter("gender"));
        String info = req.getParameter("info");

        int result = userService.insertUserToRegister(new User(0, name, gender, phone, email, password, newImage, new Date(), 0, 4, info, 2));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);

        if (result > 0) {
            jsonObject.put("result", true);
            jsonObject.put("msg", "注册成功，请前往登录~");
        } else {
            jsonObject.put("result", false);
            jsonObject.put("msg", "注册失败，请联系管理员~");
        }
        resp.setContentType("application/json;");
        resp.getWriter().write(jsonObject.toString());
    }

    // 删除用户
    public void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int result = userService.deleteUserById(Integer.parseInt(req.getParameter("id")));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        String info = result > 0 ? "删除成功" : "删除失败";
        jsonObject.put("info", info);
        resp.setContentType("application/json;");
        resp.getWriter().write(jsonObject.toString());
    }

    // 查询全部用户
    public void queryAllUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HashMap<String, Object> map = new HashMap<>();
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String gender = req.getParameter("gender");
        String type = req.getParameter("type");

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

        resp.setContentType("application/json;");
        resp.getWriter().write(jsonObject.toString());
    }

    // 获取用户角色类型
    public void queryAllRole(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Role> allRoles = roleService.getAllRoles(1, 5);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list", allRoles);
        resp.setContentType("application/json;");
        resp.getWriter().write(jsonObject.toString());
    }

    // 短信验证码
    public void sendCode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("进入发送验证码的controller");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "验证码获取成功：UserController");
        resp.setContentType("application/json;");
        resp.getWriter().write(jsonObject.toString());
    }
}