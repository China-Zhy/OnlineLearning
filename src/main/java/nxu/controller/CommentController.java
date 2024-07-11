package nxu.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nxu.entity.Comment;
import nxu.entity.Notice;
import nxu.service.CommentService;
import nxu.service.CommentServiceImpl;
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

    public void queryAllComment(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        HashMap<String, Object> map = new HashMap<>();

        String userid = req.getParameter("userid");
        String courseid = req.getParameter("courseid");
        String time = req.getParameter("time");


        System.out.println("userid=" + userid);
        System.out.println("courseid=" + courseid);
        System.out.println("time=" + time);

        if (userid != null && !userid.isEmpty()) {
            map.put("userId", userid);
        }
        if (courseid != null && !courseid.isEmpty()) {
                map.put("courseId", courseid);
        }
        if (time != null && !time.isEmpty()) {
            map.put("time", time);
        }


        List<Comment> commentList = commentService.getComment(map);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "success");
        jsonObject.put("count", commentList.size());
        jsonObject.put("data", commentList);

        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

    //删除评论
    public void deleteComment(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        resp.setContentType("application/json; charset=utf-8");
        String id = req.getParameter("id");
        int result = commentService.deleteComment(Integer.parseInt(id));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        String info = result > 0 ? "删除成功" : "删除失败";
        jsonObject.put("info", info);
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

}
