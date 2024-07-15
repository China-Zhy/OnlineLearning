package nxu.controller;

import com.alibaba.fastjson2.JSONObject;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nxu.entity.Points;
import nxu.service.PointsService;
import nxu.service.impl.PointsServiceImpl;
import nxu.utils.BaseServlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * 积分相关功能的控制器 (胡昊)
 */
@WebServlet("/points/*")
public class PointsController extends BaseServlet {

    private static final PointsService pointsService = new PointsServiceImpl();

    // 查询全部积分
    public void queryAllPoints(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageIndex", 1);
        map.put("pageSize", 10);

        String userId = req.getParameter("userId");
        String type = req.getParameter("type");
        String time = req.getParameter("time");

        if (userId != null && !userId.isEmpty()) {
            map.put("userId", userId);
        }
        if (type != null && !type.isEmpty()) {
            if (Integer.parseInt(type) != 0) {
                map.put("type", Integer.parseInt(type));
            }
        }
        if (time != null && !time.isEmpty()) {
            map.put("time", time);
        }

        JSONObject jsonObject = new JSONObject();
        PageInfo<Points> allPoints = pointsService.getAllPoints(map);

        jsonObject.put("code", 0);
        jsonObject.put("msg", "success");
        jsonObject.put("count", allPoints.getTotal());
        jsonObject.put("data", allPoints.getList());
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

    // 获取单个积分信息
    public void getOnePoints(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JSONObject jsonObject = new JSONObject();
        Points points = pointsService.getPointsById(Integer.parseInt(req.getParameter("id")));
        jsonObject.put("data", points);

        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

    // 删除积分
    public void deletePoints(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int result = pointsService.deletePointsById(Integer.parseInt(req.getParameter("id")));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        String info = result > 0 ? "删除成功" : "删除失败";
        jsonObject.put("info", info);
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

    // 添加积分
    public void insertPoints(HttpServletRequest req, HttpServletResponse resp) throws IOException, ParseException {
        Points points = new Points();

        points.setUserId(Integer.parseInt(req.getParameter("userId")));
        points.setType(req.getParameter("type"));
        points.setNumber(Integer.parseInt(req.getParameter("number")));

        // 时间字符串转为Date类型
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = format.parse(req.getParameter("time"));

        points.setTime(time);

        JSONObject jsonObject = new JSONObject();
        int result = pointsService.insertPoints(points);
        jsonObject.put("result", result);
        String info = result > 0 ? "添加成功" : "添加失败";
        jsonObject.put("info", info);

        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

    // 修改积分
    public void updatePoints(HttpServletRequest req, HttpServletResponse resp) throws IOException, ParseException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", Integer.parseInt(req.getParameter("userId")));
        map.put("type", Integer.parseInt(req.getParameter("type")));
        map.put("number", Integer.parseInt(req.getParameter("number")));
        map.put("id", Integer.parseInt(req.getParameter("id")));

        // 时间字符串转为Date类型
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = format.parse(req.getParameter("time"));

        map.put("time", time);
        int result = pointsService.updatePoints(map);

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("result", result);
        String info = result > 0 ? "更新成功" : "更新失败";
        jsonObject.put("info", info);

        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }
}