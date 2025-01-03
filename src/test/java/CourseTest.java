import com.github.pagehelper.PageInfo;
import nxu.entity.Course;
import nxu.service.CourseService;
import nxu.service.impl.CourseServiceImpl;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 课程相关功能测试 (樊雪儿)
 */
public class CourseTest {

    private static final CourseService courseService = new CourseServiceImpl();

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
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "语言");
        map.put("pageIndex", 1);
        map.put("pageSize", 10);
        PageInfo<Course> allCourses = courseService.getCourse(map);
        for (Course course : allCourses.getList()) {
            System.out.println(course);
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
        int id = 1;
        int result = courseService.deleteCourse(id);
        System.out.println(result);
    }

    /**
     * 测试获取用户拥有课程集合
     */
    @Test
    public void Test5() {
        int id = 4;
        PageInfo<Course> result = courseService.userCourse(id);
        for (Course course : result.getList()) {
            System.out.println(course);
        }
    }

    @Test
    public void Test6() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", 3);
        map.put("pageIndex", 1);
        map.put("pageSize", 3);
        PageInfo<Course> myCourse = courseService.getMyCourse(map);
        for (Course course : myCourse.getList()) {
            System.out.println(course);
        }
        System.out.println("总条数：" + myCourse.getTotal());
        System.out.println("总页数：" + myCourse.getPages());
        System.out.println("第几页：" + myCourse.getPageNum());
    }
}