import nxu.bo.CommentBo;
import nxu.bo.CourseDataBo;
import nxu.bo.CourseInfoBo;
import nxu.business.AppCourseService;
import nxu.business.AppCourseServiceImpl;
import nxu.entity.Course;
import org.junit.Test;

import java.util.List;

/**
 * 课程页面信息数据测试 (张宏业)
 */
public class AppCourseTest {

    private static final AppCourseService appCourseService = new AppCourseServiceImpl();

    @Test
    public void test1() {
        List<CourseDataBo> courseDataBo = appCourseService.getCourseDataBo();
        for (CourseDataBo courseDataBo1 : courseDataBo) {
            System.out.println("课程类型信息：" + courseDataBo1.getCourseType());
            for (Course course : courseDataBo1.getCourses()) {
                System.out.println(courseDataBo1.getCourseType().getId() + "---课程信息:" + course);
            }
        }
    }

    @Test
    public void test2() {
        CourseInfoBo courseInfoBo = appCourseService.getCourseInfoBo(1);
        System.out.println("课程信息：" + courseInfoBo.getCourse());
        System.out.println("任课教师：" + courseInfoBo.getTeacher());
        for (CommentBo commentBo : courseInfoBo.getCommentBo()) {
            System.out.println("评论信息：" + commentBo);
        }
    }
}