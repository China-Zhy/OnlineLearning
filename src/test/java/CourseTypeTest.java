import nxu.entity.CourseType;
import nxu.service.CourseTypeServiceImpl;
import org.junit.Test;

/**
 * 测试课程类型 (樊雪儿)
 */
public class CourseTypeTest {

    CourseTypeServiceImpl courseTypeImpl = new CourseTypeServiceImpl();

    /**
     * 增添课程类型
     */
    @Test
    public void test1() {
        CourseType courseType = new CourseType();
        courseType.setName("娱乐类");
        int result = courseTypeImpl.insertCourseType(courseType);
        System.out.println(result);

    }

    /**
     * 删除课程类型
     */
    @Test
    public void test2() {
        int id = 6;
        int result = courseTypeImpl.deleteCourseType(id);
        System.out.println(result);
    }

    /**
     * 获取课程类型名称
     */
    @Test
    public void test3() {
        int id = 6;
        String result = courseTypeImpl.getCourseType(id);
        System.out.println(result);
    }
}