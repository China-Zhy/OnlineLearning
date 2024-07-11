package nxu.controller;

import com.alibaba.fastjson2.JSONObject;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nxu.entity.File;
import nxu.entity.FileType;
import nxu.service.FileService;
import nxu.service.FileServiceImpl;
import nxu.service.FileTypeService;
import nxu.service.FileTypeServiceImpl;
import nxu.utils.BaseServlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * 文件相关功能的控制器 (张宏业)
 */
@WebServlet("/file/*")
public class FileController extends BaseServlet {

    private static final FileService fileService = new FileServiceImpl();

    private static final FileTypeService fileTypeService = new FileTypeServiceImpl();

    // 分页查询文件
    public void queryAllFile(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HashMap<String, Object> map = new HashMap<>();
        String name = req.getParameter("name");
        String upload = req.getParameter("upload");
        String type = req.getParameter("type");
        String state = req.getParameter("state");
        String entity = req.getParameter("entity");

        if (name != null && !name.isEmpty()) {
            map.put("name", name);
        }
        if (upload != null && !upload.isEmpty()) {
            map.put("upload", upload);
        }
        if (type != null && !type.isEmpty()) {
            if (Integer.parseInt(type) != 0) {
                map.put("type", Integer.parseInt(type));
            }
        }
        if (state != null && !state.isEmpty()) {
            if (Integer.parseInt(state) != 0) {
                map.put("state", Integer.parseInt(state));
            }
        }
        if (entity != null && !entity.isEmpty()) {
            if (Integer.parseInt(entity) != 0) {
                map.put("entity", Integer.parseInt(entity));
            }
        }

        map.put("target", 0);
        map.put("pageIndex", 1);
        map.put("pageSize", 10);
        PageInfo<File> allFiles = fileService.getAllFiles(map);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "success");
        jsonObject.put("count", allFiles.getTotal());
        jsonObject.put("data", allFiles.getList());

        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

    // 获取文件类型
    public void queryAllFileType(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<FileType> allFileType = fileTypeService.getAllFileType(0, 0);  // 全部类型
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list", allFileType);
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

    // 删除文件
    public void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int result = fileService.deleteFileById(Integer.parseInt(req.getParameter("id")));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        String info = result > 0 ? "删除成功" : "删除失败";
        jsonObject.put("info", info);
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonObject.toString());
    }

}