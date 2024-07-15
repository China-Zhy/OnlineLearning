package nxu.controller;

import com.alibaba.fastjson2.JSONObject;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nxu.entity.Message;
import nxu.service.MessageService;
import nxu.service.impl.MessageServiceImpl;
import nxu.utils.BaseServlet;

import java.io.IOException;
import java.util.HashMap;

/**
 * 消息相关功能的控制器 (唐馨源)
 */
@WebServlet("/message/*")
public class MessageController extends BaseServlet {

    public static final MessageService messageService = new MessageServiceImpl();

    // 查询用户之间的消息
    public void queryAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HashMap<String, Object> map = new HashMap<>();
        if (req.getParameter("sender") != null && req.getParameter("accept") != null && req.getParameter("pageIndex") != null && req.getParameter("pageSize") != null) {
            map.put("sender", req.getParameter("sender"));
            map.put("accept", req.getParameter("accept"));
            map.put("pageIndex", req.getParameter("pageIndex"));
            map.put("pageSize", req.getParameter("pageSize"));
        }
        map.put("sender", 3);
        map.put("accept", 4);
        map.put("pageIndex", 1);
        map.put("pageSize", 2);
        PageInfo<Message> messageList = messageService.getMessages(map);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "success");
        jsonObject.put("count", messageList.getTotal());
        jsonObject.put("data", messageList);
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }
}