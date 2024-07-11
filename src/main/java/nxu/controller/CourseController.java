package nxu.controller;

import com.alibaba.fastjson2.JSONObject;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nxu.entity.Course;
import nxu.entity.CourseCode;
import nxu.entity.User;
import nxu.service.CourseService;
import nxu.service.CourseServiceImpl;
import nxu.utils.BaseServlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;

/**
 * 课程相关功能的控制器 (樊雪儿)
 */
@WebServlet("/course/*")
public class CourseController extends BaseServlet {
    private static final CourseService courseService=new CourseServiceImpl();

    //查询课程
    public void queryCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        out.println("id="+req.getParameter("id"));
        Map<String,Object> map=new HashMap<>();
        if(req.getParameter("id")!=null&&!req.getParameter("id").equals("")){
            map.put("id",req.getParameter("id"));
        }
        if(req.getParameter("name")!=null&& req.getParameter("state")!=""){
            map.put("name",req.getParameter("name"));
        }
        if(req.getParameter("courseType")!=null && !req.getParameter("courseType").isEmpty()){
            if(Integer.parseInt(req.getParameter("courseType"))>0){
                map.put("courseType",req.getParameter("courseType"));
            }
        }
        if(req.getParameter("state")!=null && req.getParameter("state")!=""){
            if(Integer.parseInt(req.getParameter("state"))>0){
                map.put("state",req.getParameter("state"));
            }
        }
        map.put("pageIndex",1);
        map.put("pageSize",10);
        PageInfo<Course> courseList=courseService.getCourse(map);
        out.println("here");
        out.println(map);
//        resp.setContentType("text/html;charset=utf-8");
//        for(Course course:courseList.getList()){
//            resp.getWriter().println(course.toString());
//            resp.getWriter().println("<hr>");
//        }
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","success");
        jsonObject.put("count",courseList.getTotal());
        jsonObject.put("data",courseList.getList());
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

    public void deleteCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int result = courseService.deleteCourse(Integer.parseInt(req.getParameter("id")));
        out.println(result);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        String info = result > 0 ? "删除成功" : "删除失败";
        jsonObject.put("info", info);
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }
}
