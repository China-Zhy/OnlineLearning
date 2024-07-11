package nxu.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nxu.entity.Notice;
import nxu.entity.User;
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

        System.out.println("title=" + title);
        System.out.println("type=" + type);
        System.out.println("create=" + create);
        System.out.println("dateline=" + dateline);

        if (title != null && !title.isEmpty()) {
            map.put("title", title);
        }
        if (type != null && !type.isEmpty()) {
            if (Integer.parseInt(type) >= 0) {
                map.put("target", type);
            }
        }
        if (create != null && !create.isEmpty()) {
            map.put("create", create);
        }
        if (dateline != null && !dateline.isEmpty()) {
            map.put("dateline", dateline);
        }
        List<Notice> noticeList = noticeService.getNotice(map);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "success");
        jsonObject.put("count", noticeList.size());
        jsonObject.put("data", noticeList);

        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

    //删除公告
    public void deleteNotice(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json; charset=utf-8");
        String id = req.getParameter("id");
        int result = noticeService.deleteNotice(Integer.parseInt(id));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        String info = result > 0 ? "删除成功" : "删除失败";
        jsonObject.put("info", info);
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

    //添加公告
    public void addNotice(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json; charset=utf-8");
        System.out.println("添加公告");
        String title = req.getParameter("title");
        String info = req.getParameter("info");
        String dateline = req.getParameter("dateline");
      /*  String target = req.getParameter("target");*/
        String userId = req.getParameter("userId");
        System.out.println("公告收到的参数：" + title + info + dateline + userId);
        JSONObject jsonObject = JSON.parseObject(req.getReader().readLine());
        int result = noticeService.insertNotice(jsonObject);
        jsonObject.put("result", result);

    }
}
