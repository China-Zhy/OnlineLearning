import nxu.entity.Course;
import nxu.service.CourseService;
import nxu.service.CourseServiceImpl;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

/**
 * 课程相关功能测试 (樊雪儿)
 */
public class CourseTest {

    CourseService courseService = new CourseServiceImpl();

    /**
     * 测试课程增添内容功能
     */
    @Test
    public void Test1() {
        Course c1 = new Course();
        c1.setName("BackHome");
        c1.setCourseType(5);
        c1.setImage("/image/kc-5-002.png");
        c1.setInfo("这门课程旨在让学生体验回家的快乐");
        c1.setScore(500);
        c1.setState(2);
        c1.setUserId(3);
        int result = courseService.insertCourse(c1);
        System.out.println(result);
    }

    /**
     * 测试课程按照ID搜索功能
     */
    @Test
    public void Test2() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", 2);
        hashMap.put("name", "C语言程序设计");
        List<Course> resultCourses = courseService.getCourse(hashMap);
        for (Course c : resultCourses) {
            System.out.println(c);
        }
    }

    /**
     * 测试课程更改功能
     */
    @Test
    public void Test3() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", 1);
        hashMap.put("score", 201);
        int result = courseService.updateCourse(hashMap);
        System.out.println(result);

    }

    /**
     * 测试课程删除功能
     */
    @Test
    public void Test4() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", 1);
        int result = courseService.deleteCourse(hashMap);
        System.out.println(result);
    }

}