
import com.github.pagehelper.PageInfo;
import nxu.entity.CourseCode;
import nxu.service.CourseCodeService;
import nxu.service.CourseCodeServiceImpl;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试课程邀请码相关功能 (樊雪儿)
 */
public class CourseCodeTest {

    CourseCodeService courseCodeService = new CourseCodeServiceImpl();

    /**
     * 增添课程邀请码
     */
    @Test
    public void test1() {
        CourseCode courseCode = new CourseCode();
        courseCode.setCode("NXU4FB");
        courseCode.setCourseId(6);
        courseCode.setUserId(2);
        int result = courseCodeService.insertCourseCode(courseCode);
        System.out.println(result);
    }

    /**
     * 删除课程邀请码
     */
    @Test
    public void test2() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", 2);
        map.put("courseId", 6);
        int result = courseCodeService.deleteCourseCode(map);
        System.out.println(result);
    }

    /**
     * 查找邀请码
     */
    @Test
    public void test3() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", 2);
        map.put("courseId", 6);
        map.put("pageIndex", 1);
        map.put("pageSize", 10);
        PageInfo<CourseCode> getCourseCode = courseCodeService.getCourseCode(map);
        System.out.println(getCourseCode);
    }
}