package nxu.controller;

import com.alibaba.fastjson2.JSONObject;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nxu.entity.Order;
import nxu.service.OrderService;
import nxu.service.OrderServiceImpl;
import nxu.utils.BaseServlet;

import java.io.IOException;
import java.util.HashMap;

/**
 * 订单相关功能的控制器 (胡昊)
 */
@WebServlet("/order/*")
public class OrderController extends BaseServlet {

    private static final OrderService orderService = new OrderServiceImpl();

    // 查询全部订单
    public void queryAllOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageIndex", 1);
        map.put("pageSize", 10);

        String userId = req.getParameter("userId");
        String courseId = req.getParameter("courseId");
        String time = req.getParameter("time");
        String state = req.getParameter("state");

        System.out.println("userId=" + userId);
        System.out.println("courseId=" + courseId);
        System.out.println("time=" + time);
        System.out.println("state=" + state);

        if (userId != null && !userId.isEmpty()) {
            map.put("userId", userId);
        }
        if (courseId != null && !courseId.isEmpty()) {
            map.put("courseId", courseId);
        }
        if (time != null && !time.isEmpty()) {
            map.put("time", time);
        }
        if (state != null && !state.isEmpty()) {
            if (Integer.parseInt(state) != 0) {
                map.put("state", Integer.parseInt(state));
            }
        }

        PageInfo<Order> allOrder = orderService.getAllOrder(map);
        for (Order order : allOrder.getList()) {
            System.out.println(order);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "success");
        jsonObject.put("count", allOrder.getTotal());
        jsonObject.put("data", allOrder.getList());
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

    // 删除用户
    public void deleteOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int result = orderService.deleteOrderById(Integer.parseInt(req.getParameter("id")));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        String info = result > 0 ? "删除成功" : "删除失败";
        jsonObject.put("info", info);
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }
}