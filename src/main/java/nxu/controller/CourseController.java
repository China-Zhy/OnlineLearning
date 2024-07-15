package nxu.controller;

import com.alibaba.fastjson2.JSONObject;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nxu.entity.Course;
import nxu.service.CourseService;
import nxu.service.impl.CourseServiceImpl;
import nxu.utils.BaseServlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 课程相关功能的控制器 (樊雪儿、张宏业)
 */
@WebServlet("/course/*")
public class CourseController extends BaseServlet {

    private static final CourseService courseService = new CourseServiceImpl();

    //查询课程
    public void queryCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, Object> map = new HashMap<>();

        if (req.getParameter("id") != null && !req.getParameter("id").isEmpty()) {
            map.put("id", req.getParameter("id"));
        }
        if (req.getParameter("name") != null && !Objects.equals(req.getParameter("state"), "")) {
            map.put("name", req.getParameter("name"));
        }
        if (req.getParameter("courseType") != null && !req.getParameter("courseType").isEmpty()) {
            if (Integer.parseInt(req.getParameter("courseType")) > 0) {
                map.put("courseType", req.getParameter("courseType"));
            }
        }
        if (req.getParameter("state") != null && !Objects.equals(req.getParameter("state"), "")) {
            if (Integer.parseInt(req.getParameter("state")) > 0) {
                map.put("state", req.getParameter("state"));
            }
        }
        map.put("pageIndex", 1);
        map.put("pageSize", 10);

        JSONObject jsonObject = new JSONObject();
        PageInfo<Course> courseList = courseService.getCourse(map);

        jsonObject.put("code", 0);
        jsonObject.put("msg", "success");
        jsonObject.put("count", courseList.getTotal());
        jsonObject.put("data", courseList.getList());
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

    //删除课程
    public void deleteCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JSONObject jsonObject = new JSONObject();
        int result = courseService.deleteCourse(Integer.parseInt(req.getParameter("id")));

        jsonObject.put("result", result);
        String info = result > 0 ? "删除成功" : "删除失败";
        jsonObject.put("info", info);
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

    //添加课程
    public void insertCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Course course = new Course();
        course.setName(req.getParameter("name"));
        course.setCourseType(Integer.parseInt(req.getParameter("courseType")));
        course.setImage("/layuiadmin/upload/" + req.getParameter("image"));
        course.setInfo(req.getParameter("info"));
        course.setScore(Integer.parseInt(req.getParameter("score")));
        course.setState(Integer.parseInt(req.getParameter("state")));
        course.setUserId(Integer.parseInt(req.getParameter("userId")));

        JSONObject jsonObject = new JSONObject();
        int result = courseService.insertCourse(course);

        jsonObject.put("result", result);
        String info = result > 0 ? "插入成功" : "插入失败";
        jsonObject.put("info", info);
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

    //更新课程
    public void updateCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HashMap<String, Object> map = new HashMap<>();

        map.put("id", Integer.parseInt(req.getParameter("id")));
        map.put("name", req.getParameter("name"));
        map.put("courseType", Integer.parseInt(req.getParameter("courseType")));
        map.put("image", req.getParameter("image"));
        map.put("info", req.getParameter("info"));
        map.put("score", Integer.parseInt(req.getParameter("score")));
        map.put("state", Integer.parseInt(req.getParameter("state")));
        map.put("userId", Integer.parseInt(req.getParameter("userId")));

        JSONObject jsonObject = new JSONObject();
        int result = courseService.updateCourse(map);

        jsonObject.put("result", result);
        String info = result > 0 ? "更新成功" : "更新失败";
        jsonObject.put("info", info);

        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }
}