package nxu.controller;

import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nxu.entity.Notice;
import nxu.service.NoticeService;
import nxu.service.NoticeServiceImpl;
import nxu.utils.BaseServlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * 公告相关功能的控制器 (唐馨源)
 */
@WebServlet("/notice/*")
public class NoticeController extends BaseServlet {
    public static final NoticeService noticeService = new NoticeServiceImpl();

    // 查询所有公告
    public void queryAllNotice(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HashMap<String, Object> map = new HashMap<>();

        String title = req.getParameter("title");
        String type = req.getParameter("type");
        String create = req.getParameter("create");
        String dateline = req.getParameter("dateline");

        if (title != null && !title.isEmpty()) {
            map.put("title", title);
        }
        if (type != null && !type.isEmpty()) {
            if (Integer.parseInt(type) != 1) {
                map.put("target", type);
            }
        }
        if (create != null && !create.isEmpty()) {
            map.put("create", create);
        }
        if (dateline != null && !dateline.isEmpty()) {
            map.put("dateline", dateline);
        }
        JSONObject jsonObject = new JSONObject();
        List<Notice> noticeList = noticeService.getNotice(map);
        jsonObject.put("code", 0);
        jsonObject.put("msg", "success");
        jsonObject.put("count", noticeList.size());
        jsonObject.put("data", noticeList);

        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

    //删除公告
    public void deleteNotice(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        JSONObject jsonObject = new JSONObject();
        int result = noticeService.deleteNotice(Integer.parseInt(id));
        jsonObject.put("result", result);
        String info = result > 0 ? "删除成功" : "删除失败";
        jsonObject.put("info", info);
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

    //添加公告
    public void addNotice(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HashMap<String, Object> map = new HashMap<>();
        String title = req.getParameter("title");
        String info = req.getParameter("info");
        String dateline = req.getParameter("dateline");
        String target = req.getParameter("course");
        String userId = req.getParameter("userId");
        if (title != null && !title.isEmpty()) {
            map.put("title", title);
        }
        if (info != null && !info.isEmpty()) {
            map.put("info", info);
        }
        if (target != null && !target.isEmpty()) {
            map.put("target", target);
        }
        if (userId != null && !userId.isEmpty()) {
            map.put("userId", userId);
        }
        if (dateline != null && !dateline.isEmpty()) {
            map.put("dateline", dateline);
        }
        JSONObject jsonObject = new JSONObject();
        int result = noticeService.insertNotice(map);
        jsonObject.put("result", result);
        String data = result > 0 ? "添加成功" : "添加失败";
        jsonObject.put("info", data);

        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

    // 获取单个公告消息
    public void getOneNotice(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JSONObject jsonObject = new JSONObject();
        Notice notice = noticeService.getOneNotice(Integer.parseInt(req.getParameter("id")));
        jsonObject.put("data", notice);
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

    // 更新公告消息
    public void updateNotice(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HashMap<String, Object> map = new HashMap<>();
        String title = req.getParameter("title");
        String info = req.getParameter("info");
        String dateline = req.getParameter("dateline");
        String target = req.getParameter("target");
        String userId = req.getParameter("userId");
        if (req.getParameter("id") != null) {
            map.put("id", Integer.parseInt(req.getParameter("id")));
        }
        if (title != null && !title.isEmpty()) {
            map.put("title", title);
        }
        if (info != null && !info.isEmpty()) {
            map.put("info", info);

        }
        if (target != null && !target.isEmpty()) {
            map.put("target", target);
        }
        if (userId != null && !userId.isEmpty()) {
            map.put("userId", userId);
        }
        if (dateline != null && !dateline.isEmpty()) {
            map.put("dateline", dateline);
        }
        JSONObject jsonObject = new JSONObject();
        int result = noticeService.updateNotice(map);
        jsonObject.put("result", result);
        String data = result > 0 ? "更新成功" : "更新失败";
        jsonObject.put("info", data);
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }
}
