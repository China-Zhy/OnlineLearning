import com.github.pagehelper.PageInfo;
import nxu.entity.Homework;
import nxu.service.HomeworkService;
import nxu.service.HomeworkServiceImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 作业功能测试 (张宏业)
 */
public class HomeworkTest {

    HomeworkService homeworkService = new HomeworkServiceImpl();

    @Test
    public void test1() {
        Homework homework = homeworkService.getHomeworkById(1);
        System.out.println(homework);
    }

    @Test
    public void test2() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageIndex", 1);
        map.put("pageSize", 100);
        map.put("create", "2024-06-07");
        map.put("dateline", "2024-07-11");
        PageInfo<Homework> allHomework = homeworkService.getAllHomework(map);
        for (Homework homework : allHomework.getList()) {
            System.out.println(homework);
        }
    }

    @Test
    public void test3() {
        Date date = new Date("2018/03/03 00:00:00");
        Homework homework = new Homework(0, "测试作业", "一个作业", 1, 1, date, date, 1);
        int i = homeworkService.insertHomework(homework);
        System.out.println(i);
    }

    @Test
    public void test4() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", 3);
        map.put("title", "新的测试作业");
        map.put("info", "新的作业信息");
        Date date = new Date("2018/03/03 01:01:01");
        map.put("dateline", date);
        map.put("again", 0);
        int i = homeworkService.updateHomework(map);
        System.out.println(i);
    }

    @Test
    public void test5() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        int i = homeworkService.deleteHomeworkById(list);
        System.out.println(i);
    }
}