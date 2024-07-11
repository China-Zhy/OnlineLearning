package nxu.controller;

import com.alibaba.fastjson2.JSONObject;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nxu.entity.Course;
import nxu.entity.CourseType;
import nxu.service.CourseTypeService;
import nxu.service.CourseTypeServiceImpl;
import nxu.utils.BaseServlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

/**
 * 课程类型相关功能的控制器 (樊雪儿)
 */
@WebServlet("/courseType/*")
public class CourseTypeController extends BaseServlet {
    private static final CourseTypeService courseTypeService = new CourseTypeServiceImpl();

    //查询课程类型
    public void queryCourseType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String,Object> map=new HashMap<>();
        map.put("pageIndex",1);
        map.put("pageSize",10);
        PageInfo<CourseType> courseType = courseTypeService.getCourseType(map);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","success");
        jsonObject.put("count",courseType.getTotal());
        jsonObject.put("data",courseType.getList());
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(jsonObject.toString());
    }
    //删除课程类型
    public void deleteCourseType(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int result = courseTypeService.deleteCourseType(Integer.parseInt(req.getParameter("id")));
        out.println(result);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        String info = result > 0 ? "删除成功" : "删除失败";
        jsonObject.put("info", info);
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

    public void updateCourseType(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
    }
}
