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

/**
 * 课程类型相关功能的控制器 (樊雪儿)
 */
@WebServlet("/courseType/*")
public class CourseTypeController extends BaseServlet {
    private static final CourseTypeService courseTypeService = new CourseTypeServiceImpl();

    //查询课程类型
    public void queryCourseType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String,Object> map=new HashMap<>();
        if(request.getParameter("id")!=null&&!request.getParameter("id").equals("")){
            if(Integer.parseInt(request.getParameter("id"))>0){
                map.put("id",Integer.parseInt(request.getParameter("id")));
            }
        }
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
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        String info = result > 0 ? "删除成功" : "删除失败";
        jsonObject.put("info", info);
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

    public void insertCourseType(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CourseType courseType = new CourseType();
        courseType.setName(req.getParameter("name"));

        int result=courseTypeService.insertCourseType(courseType);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        String info = result > 0 ? "插入成功" : "插入失败";
        jsonObject.put("info", info);
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }
}
