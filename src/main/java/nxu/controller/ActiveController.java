package nxu.controller;

import com.alibaba.fastjson2.JSONObject;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nxu.entity.Active;
import nxu.entity.User;
import nxu.service.ActiveService;
import nxu.service.impl.ActiveServiceImpl;
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
    public void queryAllActive(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageIndex", 1);
        map.put("pageSize", 12);

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

        JSONObject jsonObject = new JSONObject();
        PageInfo<Active> allActive = activeService.getAllActive(map);

        jsonObject.put("code", 0);
        jsonObject.put("msg", "success");
        jsonObject.put("count", allActive.getTotal());
        jsonObject.put("data", allActive.getList());

        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

    // 获取单个活动信息
    public void getOneActive(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JSONObject jsonObject = new JSONObject();
        Active active = activeService.getActiveById(Integer.parseInt(req.getParameter("id")));

        jsonObject.put("data", active);

        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

    // 删除活动
    public void deleteActive(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JSONObject jsonObject = new JSONObject();
        int result = activeService.deleteActiveById(Integer.parseInt(req.getParameter("id")));
        jsonObject.put("result", result);
        String info = result > 0 ? "删除成功" : "删除失败";
        jsonObject.put("info", info);
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

    // 添加活动
    public void insertActive(HttpServletRequest req, HttpServletResponse resp) throws IOException, ParseException {
        Active active = new Active();
        active.setImage("/layuiadmin/upload/" + req.getParameter("image"));
        active.setTitle(req.getParameter("title"));
        active.setUserId(((User) req.getSession().getAttribute("Admin")).getId());
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
        map.put("image", "/layuiadmin/upload/" + req.getParameter("image"));
        map.put("title", req.getParameter("title"));
        map.put("discount", req.getParameter("discount"));
        map.put("info", req.getParameter("info"));
        map.put("id", Integer.parseInt(req.getParameter("id")));
        map.put("userId", ((User) req.getSession().getAttribute("Admin")).getId());

        // 时间字符串转为Date类型
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date create = format.parse(req.getParameter("create"));
        Date deadline = format.parse(req.getParameter("deadline"));

        map.put("create", create);
        map.put("deadline", deadline);

        JSONObject jsonObject = new JSONObject();
        int result = activeService.updateActive(map);

        jsonObject.put("result", result);
        String info = result > 0 ? "更新成功" : "更新失败";
        jsonObject.put("info", info);

        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }
}