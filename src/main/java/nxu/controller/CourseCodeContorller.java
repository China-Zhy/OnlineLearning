package nxu.controller;

import com.alibaba.fastjson2.JSONObject;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nxu.entity.CourseCode;
import nxu.service.CourseCodeService;
import nxu.service.impl.CourseCodeServiceImpl;
import nxu.utils.BaseServlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 课程邀请码相关功能的控制器 (樊雪儿)
 */
@WebServlet("/courseCode/*")
public class CourseCodeContorller extends BaseServlet {
    private static final CourseCodeService courseCodeService = new CourseCodeServiceImpl();

    //查询课程邀请码
    public void queryCourseCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> map = new HashMap<>();
        //获取参数
        if (request.getParameter("userId") != null && request.getParameter("userId") != "") {
            map.put("userId", Integer.parseInt(request.getParameter("userId")));
        }
        if (request.getParameter("courseId") != null && request.getParameter("courseId") != "") {
            map.put("courseId", Integer.parseInt(request.getParameter("courseId")));
        }
        map.put("pageIndex", 1);
        map.put("pageSize", 10);
        PageInfo<CourseCode> result = courseCodeService.getCourseCode(map);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "success");
        jsonObject.put("count", result.getTotal());
        jsonObject.put("data", result.getList());
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(jsonObject.toString());

    }

    public void deleteCourseCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", request.getParameter("user_id"));
        map.put("courseId", request.getParameter("course_id"));
        int result = courseCodeService.deleteCourseCode(map);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        String info = result > 0 ? "删除成功" : "删除失败";
        jsonObject.put("info", info);
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(jsonObject.toString());
    }

    public void insertCourseCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CourseCode courseCode = new CourseCode();
        courseCode.setUserId(Integer.parseInt(request.getParameter("userId")));
        courseCode.setCourseId(Integer.parseInt(request.getParameter("courseId")));
        courseCode.setCode(request.getParameter("code"));
        int result = courseCodeService.insertCourseCode(courseCode);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        String info = result > 0 ? "插入成功" : "插入失败";
        jsonObject.put("info", info);
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(jsonObject.toString());
    }
}
