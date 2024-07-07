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

}