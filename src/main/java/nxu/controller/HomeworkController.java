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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        map.put("pageSize", 100);

        JSONObject jsonObject = new JSONObject();

        PageInfo<Homework> allHomework = homeworkService.getAllHomework(map);

        jsonObject.put("code", 0);
        jsonObject.put("msg", "success");
        jsonObject.put("count", allHomework.getTotal());
        jsonObject.put("data", allHomework.getList());

        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

    // 获取单个作业信息
    public void getOneHomework(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        JSONObject jsonObject = new JSONObject();
        Homework homework = homeworkService.getHomeworkById(Integer.parseInt(req.getParameter("id")));

        jsonObject.put("data", homework);

        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

    // 删除作业
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

    // 添加作业
    public void insertHomework(HttpServletRequest req, HttpServletResponse resp) throws IOException, ParseException {
        Homework homework = new Homework();

        homework.setTitle(req.getParameter("title"));
        homework.setInfo(req.getParameter("info"));
        homework.setCourseId(Integer.parseInt(req.getParameter("courseId")));
        homework.setUserId(Integer.parseInt(req.getParameter("userId")));
        homework.setAgain(Integer.parseInt(req.getParameter("again")));

        // 时间字符串转为Date类型
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date create = format.parse(req.getParameter("create"));
        Date dateline = format.parse(req.getParameter("dateline"));

        homework.setCreate(create);
        homework.setDateline(dateline);

        JSONObject jsonObject = new JSONObject();
        int result = homeworkService.insertHomework(homework);
        jsonObject.put("result", result);
        String info = result > 0 ? "添加成功" : "添加失败";
        jsonObject.put("info", info);

        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

    // 修改作业
    public void updateHomework(HttpServletRequest req, HttpServletResponse resp) throws IOException, ParseException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", Integer.parseInt(req.getParameter("id")));
        map.put("title", req.getParameter("title"));
        map.put("info", req.getParameter("info"));
        map.put("courseId", Integer.parseInt(req.getParameter("courseId")));
        map.put("userId", Integer.parseInt(req.getParameter("userId")));
        map.put("again", Integer.parseInt(req.getParameter("again")));

        // 时间字符串转为Date类型
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date create = format.parse(req.getParameter("create"));
        Date dateline = format.parse(req.getParameter("dateline"));

        map.put("create", create);
        map.put("dateline", dateline);

        int result = homeworkService.updateHomework(map);

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("result", result);
        String info = result > 0 ? "更新成功" : "更新失败";
        jsonObject.put("info", info);

        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }
}