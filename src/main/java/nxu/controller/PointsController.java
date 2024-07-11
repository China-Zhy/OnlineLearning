package nxu.controller;

import com.alibaba.fastjson2.JSONObject;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nxu.entity.Points;
import nxu.service.PointsService;
import nxu.service.PointsServiceImpl;
import nxu.utils.BaseServlet;

import java.io.IOException;
import java.util.HashMap;

/**
 * 积分相关功能的控制器 (胡昊)
 */
@WebServlet("/points/*")
public class PointsController extends BaseServlet {

    private static final PointsService pointsService = new PointsServiceImpl();

    // 查询全部积分
    public void queryAllPoints(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageIndex", 1);
        map.put("pageSize", 10);

        String userId = req.getParameter("userId");
        String type = req.getParameter("type");
        String time = req.getParameter("time");

        System.out.println("userId=" + userId);
        System.out.println("type=" + type);
        System.out.println("time=" + time);

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

        PageInfo<Points> allPoints = pointsService.getAllPoints(map);
        for (Points points : allPoints.getList()) {
            System.out.println(points);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "success");
        jsonObject.put("count", allPoints.getTotal());
        jsonObject.put("data", allPoints.getList());
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

    // 删除用户
    public void deletePoints(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int result = pointsService.deletePointsById(Integer.parseInt(req.getParameter("id")));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        String info = result > 0 ? "删除成功" : "删除失败";
        jsonObject.put("info", info);
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }
}