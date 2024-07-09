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

/**
 * 作业相关功能的控制器 (张宏业)
 */
@WebServlet("/homework/*")
public class HomeworkController extends BaseServlet {

    private static final HomeworkService homeworkService = new HomeworkServiceImpl();

    // 分页查询作业
    public void queryAllHomework(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PageInfo<Homework> allHomework = homeworkService.getAllHomework(0, 0, 1, 5);
        System.out.println(allHomework.getTotal());
        System.out.println(allHomework.getPages());
        System.out.println(allHomework.getPageNum());
        System.out.println(allHomework.getPageSize());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total", allHomework.getTotal());
        jsonObject.put("pages", allHomework.getPages());
        jsonObject.put("pageNum", allHomework.getPageNum());
        jsonObject.put("pageSize", allHomework.getPageSize());
        jsonObject.put("allHomework", allHomework.getList());
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }
}