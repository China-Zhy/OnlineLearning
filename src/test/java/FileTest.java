import com.github.pagehelper.PageInfo;
import nxu.entity.File;
import nxu.service.FileService;
import nxu.service.impl.FileServiceImpl;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;

/**
 * 文件相关功能测试 (张宏业)
 */
public class FileTest {

    private static final FileService fileService = new FileServiceImpl();


    @Test
    public void test1() {
        File fileById = fileService.getFileById(1);
        System.out.println(fileById);
    }

    @Test
    public void test2() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("entity", 0);
        map.put("target", 0);
        map.put("pageIndex", 1);
        map.put("pageSize", 100);
        PageInfo<File> allFiles = fileService.getAllFiles(map);
        for (File file : allFiles.getList()) {
            System.out.println(file);
        }
    }

    @Test
    public void test3() {
        Date date = new Date("2020/01/01 12:12:12");
        File file = new File(0, "测试文件", "D:\\test.txt", date, 1, "一个文件", 0);
        int i = fileService.insertFile(file);
        System.out.println(i);
    }

    @Test
    public void test4() {
        int i = fileService.deleteFileById(3);
        System.out.println(i);
    }
}