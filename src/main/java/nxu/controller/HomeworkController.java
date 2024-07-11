package nxu.controller;

import com.alibaba.fastjson2.JSONObject;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nxu.entity.Homework;
import nxu.service.HomeworkService;
import nxu.service.HomeworkServiceImpl;
import nxu.utils.BaseServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 作业相关功能的控制器 (张宏业)
 */
@WebServlet("/homework/*")
public class HomeworkController extends BaseServlet {

    private static final HomeworkService homeworkService = new HomeworkServiceImpl();

    // 分页查询作业
    public void queryAllHomework(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HashMap<String, Object> map = new HashMap<>();
        String title = req.getParameter("title");
        String courseId = req.getParameter("courseId");
        String userId = req.getParameter("userId");
        String create = req.getParameter("create");
        String dateline = req.getParameter("dateline");
        String again = req.getParameter("again");

        if (title != null && !title.isEmpty()) {
            map.put("title", title);
        }
        if (create != null && !create.isEmpty()) {
            map.put("create", create);
        }
        if (dateline != null && !dateline.isEmpty()) {
            map.put("dateline", dateline);
        }
        if (courseId != null && !courseId.isEmpty()) {
            if (Integer.parseInt(courseId) != 0) {
                map.put("courseId", Integer.parseInt(courseId));
            }
        }
        if (userId != null && !userId.isEmpty()) {
            if (Integer.parseInt(userId) != 0) {
                map.put("userId", Integer.parseInt(userId));
            }
        }
        if (again != null && !again.isEmpty()) {
            if (Integer.parseInt(again) != 0) {
                map.put("again", Integer.parseInt(again));
            }
        }

        map.put("pageIndex", 1);
        map.put("pageSize", 10);
        PageInfo<Homework> allHomework = homeworkService.getAllHomework(1, 1, 1, 10);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "success");
        jsonObject.put("count", allHomework.getTotal());
        jsonObject.put("data", allHomework.getList());

        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

    // 删除文件
    public void deleteHomework(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(Integer.parseInt(req.getParameter("id")));

        JSONObject jsonObject = new JSONObject();
        int result = homeworkService.deleteHomeworkById(list);

        jsonObject.put("result", result);
        String info = result > 0 ? "删除成功" : "删除失败";
        jsonObject.put("info", info);

        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }
}