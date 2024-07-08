import nxu.entity.FileType;
import nxu.service.FileTypeService;
import nxu.service.FileTypeServiceImpl;
import org.junit.Test;

import java.util.List;

/**
 * 文件类型功能测试 (张宏业)
 */
public class FileTypeTest {

    FileTypeService fileTypeService = new FileTypeServiceImpl();

    @Test
    public void test1() {
        String fileTypeNameById = fileTypeService.getFileTypeNameById(1);
        System.out.println(fileTypeNameById);
    }

    @Test
    public void test2() {
        List<FileType> allFileType = fileTypeService.getAllFileType(2, 3);
        for (FileType fileType : allFileType) {
            System.out.println(fileType);
        }
    }

    @Test
    public void test3() {
        int i = fileTypeService.insertFileType("测试类型名");
        System.out.println(i);
    }

    @Test
    public void test4() {
        int i = fileTypeService.updateFileType(1, "word");
        System.out.println(i);
    }

    @Test
    public void test5() {
        int i = fileTypeService.deleteFileTypeById(6);
        System.out.println(i);
    }

}