package nxu.controller;

import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nxu.entity.Comment;
import nxu.service.CommentService;
import nxu.service.impl.CommentServiceImpl;
import nxu.utils.BaseServlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * 评论相关功能的控制器 (唐馨源)
 */
@WebServlet("/comment/*")
public class CommentController extends BaseServlet {
    public static final CommentService commentService = new CommentServiceImpl();

    //查找全部
    public void queryAllComment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HashMap<String, Object> map = new HashMap<>();

        String userId = req.getParameter("userId");
        String courseId = req.getParameter("courseId");
        String time = req.getParameter("time");

        if (userId != null && !userId.isEmpty()) {
            map.put("userId", userId);
        }
        if (courseId != null && !courseId.isEmpty()) {
            map.put("courseId", courseId);
        }
        if (time != null && !time.isEmpty()) {
            map.put("time", time);
        }
        JSONObject jsonObject = new JSONObject();
        List<Comment> commentList = commentService.getComment(map);
        jsonObject.put("code", 0);
        jsonObject.put("msg", "success");
        jsonObject.put("count", commentList.size());
        jsonObject.put("data", commentList);

        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

    //删除评论
    public void deleteComment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JSONObject jsonObject = new JSONObject();
        int result = commentService.deleteComment(Integer.parseInt(req.getParameter("id")));
        jsonObject.put("result", result);
        String info = result > 0 ? "删除成功" : "删除失败";
        jsonObject.put("info", info);
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

}
