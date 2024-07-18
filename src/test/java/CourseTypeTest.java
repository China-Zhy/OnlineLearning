import com.github.pagehelper.PageInfo;
import nxu.entity.CourseType;
import nxu.service.impl.CourseTypeServiceImpl;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试课程类型 (樊雪儿)
 */
public class CourseTypeTest {

    private static final CourseTypeServiceImpl courseTypeImpl = new CourseTypeServiceImpl();

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
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageIndex", 1);
        map.put("pageSize", 10);
        PageInfo<CourseType> allCourseType = courseTypeImpl.getCourseType(map);
        for (CourseType cT : allCourseType.getList()) {
            System.out.println(cT);
        }
    }
}