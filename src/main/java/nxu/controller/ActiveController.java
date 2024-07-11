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

        System.out.println("title=" + title);
        System.out.println("userId=" + userId);
        System.out.println("create=" + create);
        System.out.println("deadline=" + deadline);

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



    // 删除用户
    public void deleteActive(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int result = activeService.deleteActiveById(Integer.parseInt(req.getParameter("id")));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        String info = result > 0 ? "删除成功" : "删除失败";
        jsonObject.put("info", info);
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }
}