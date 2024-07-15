package nxu.utils;

import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;

/**
 * 文件上传工具类 (张宏业)
 */
@MultipartConfig
@WebServlet("/uploadServlet")
public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JSONObject json = new JSONObject();
        try {
            Part part = req.getPart("file");
            String fileName = part.getSubmittedFileName();
            String realPath = getServletContext().getRealPath("/");
            part.write(realPath + "/layuiadmin/upload/" + fileName);
            json.put("fileName", fileName);
            json.put("code", 0);
            json.put("msg", "上传成功");
        } catch (Exception e) {
            json.put("code", 1);
            json.put("msg", "上传失败");
        }
        resp.setContentType("application/json;");
        resp.getWriter().write(json.toString());
    }
}