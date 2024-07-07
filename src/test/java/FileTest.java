import nxu.entity.File;
import nxu.service.FileService;
import nxu.service.FileServiceImpl;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

/**
 * 文件相关功能测试 (张宏业)
 */
public class FileTest {

    FileService fileService = new FileServiceImpl();


    @Test
    public void test1() {
        File fileById = fileService.getFileById(1);
        System.out.println(fileById);
    }

    @Test
    public void test2() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("entity", 1);
        map.put("target", 1);
        List<File> allFiles = fileService.getAllFiles(map);
        for (File file : allFiles) {
            System.out.println(file);
        }
    }

    @Test
    public void test3() {
        File file = new File(1, "《Java从入门到入土》", "D://java.txt", null, 1, "一个Java知识的笔记", 1);
        int i = fileService.insertFile(file);
        System.out.println(i);
    }

    @Test
    public void test4() {
        int i = fileService.deleteFileById(2);
        System.out.println(i);
    }
}
