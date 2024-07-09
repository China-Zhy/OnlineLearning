package nxu.controller;

import com.alibaba.fastjson2.JSONObject;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nxu.entity.File;
import nxu.service.FileService;
import nxu.service.FileServiceImpl;
import nxu.utils.BaseServlet;

import java.io.IOException;
import java.util.HashMap;

/**
 * 文件相关功能的控制器 (张宏业)
 */
@WebServlet("/file/*")
public class FileController extends BaseServlet {

    private static final FileService fileService = new FileServiceImpl();

    // 分页查询文件
    public void queryAllFile(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("entity", 1);
        map.put("target", 1);
        map.put("pageIndex", 2);
        map.put("pageSize", 1);
        PageInfo<File> allFiles = fileService.getAllFiles(map);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total", allFiles.getTotal());
        jsonObject.put("pages", allFiles.getPages());
        jsonObject.put("pageNum", allFiles.getPageNum());
        jsonObject.put("pageSize", allFiles.getPageSize());
        jsonObject.put("allFiles", allFiles.getList());
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

}