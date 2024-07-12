package nxu.controller;

import com.alibaba.fastjson2.JSONObject;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nxu.entity.Active;
import nxu.service.ActiveService;
import nxu.service.ActiveServiceImpl;
import nxu.utils.BaseServlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * 活动相关功能的控制器 (胡昊)
 */
@WebServlet("/active/*")
public class ActiveController extends BaseServlet {

    private static final ActiveService activeService = new ActiveServiceImpl();

    // 查询全部活动
    public void queryAllActive(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageIndex", 1);
        map.put("pageSize", 10);

        String title = req.getParameter("title");
        String userId = req.getParameter("userId");
        String create = req.getParameter("create");
        String deadline = req.getParameter("deadline");

        if (title != null && !title.isEmpty()) {
            map.put("title", title);
        }
        if (userId != null && !userId.isEmpty()) {
            map.put("userId", userId);
        }
        if (create != null && !create.isEmpty()) {
            map.put("create", create);
        }
        if (deadline != null && !deadline.isEmpty()) {
            map.put("deadline", deadline);
        }

        PageInfo<Active> allActive = activeService.getAllActive(map);
        for (Active active : allActive.getList()) {
            System.out.println(active);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "success");
        jsonObject.put("count", allActive.getTotal());
        jsonObject.put("data", allActive.getList());

        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

    // 获取单个活动信息
    public void getOneActive(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        if (req.getParameter("id") != null) {
            System.out.println("收到的编辑标号：" + req.getParameter("id"));
        }

        JSONObject jsonObject = new JSONObject();
        Active active = activeService.getActiveById(Integer.parseInt(req.getParameter("id")));

        jsonObject.put("data", active);

        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }



    // 删除活动
    public void deleteActive(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int result = activeService.deleteActiveById(Integer.parseInt(req.getParameter("id")));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        String info = result > 0 ? "删除成功" : "删除失败";
        jsonObject.put("info", info);
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

    // 添加活动
    public void insertActive(HttpServletRequest req, HttpServletResponse resp) throws IOException, ParseException {
        Active active = new Active();
        System.out.println(req.getParameter("userId"));

        System.out.println(req.getParameter("image"));
        System.out.println(req.getParameter("title"));
        System.out.println(req.getParameter("create"));
        System.out.println(req.getParameter("deadline"));
        System.out.println(req.getParameter("discount"));
        System.out.println(req.getParameter("info"));

        active.setImage(req.getParameter("image"));
        active.setTitle(req.getParameter("title"));
        active.setUserId(Integer.parseInt(req.getParameter("userId")));
        active.setDiscount(Integer.parseInt(req.getParameter("discount")));
        active.setInfo(req.getParameter("info"));
        // 时间字符串转为Date类型
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date create = format.parse(req.getParameter("create"));
        Date deadline = format.parse(req.getParameter("deadline"));

        active.setCreate(create);
        active.setDeadline(deadline);

        JSONObject jsonObject = new JSONObject();
        int result = activeService.insertActive(active);
        jsonObject.put("result", result);
        String info = result > 0 ? "添加成功" : "添加失败";
        jsonObject.put("info", info);

        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

    // 修改活动
    public void updateActive(HttpServletRequest req, HttpServletResponse resp) throws IOException, ParseException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("image",req.getParameter("image"));
        map.put("title", req.getParameter("title"));
        map.put("discount", req.getParameter("discount"));
        map.put("info", req.getParameter("info"));
        map.put("id", Integer.parseInt(req.getParameter("id")));
        map.put("userId", Integer.parseInt(req.getParameter("userId")));

        // 时间字符串转为Date类型
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date create = format.parse(req.getParameter("create"));
        Date deadline = format.parse(req.getParameter("deadline"));

        map.put("create", create);
        map.put("deadline", deadline);

        int result = activeService.updateActive(map);

        System.out.println(result);
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("result", result);
        String info = result > 0 ? "更新成功" : "更新失败";
        jsonObject.put("info", info);

        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }
}