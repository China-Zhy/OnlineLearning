package nxu.mapper;

import nxu.entity.Course;

import java.util.List;
import java.util.Map;

/**
 * 课程数据层接口 (樊雪儿、张宏业)
 */
public interface CourseMapper {

    /**
     * 增添课程信息
     *
     * @param course 课程实体类
     * @return insert后受影响行数
     */
    int insertCourse(Course course);

    /**
     * 获取搜索的课程集合
     *
     * @param map 实体参数 id, name, course_type, score
     * @return 返回查询的课程实体集合
     */
    List<Course> getCourse(Map<String, Object> map);

    /**
     * 更新课程内容
     *
     * @param map 实体参数（id, name, image, course_type, info, score, state)
     * @return 更改后受影响的行数
     */
    int updateCourse(Map<String, Object> map);

    /**
     * 删除相应课程
     *
     * @param id 实体参数
     * @return 删除后受影响的行数
     */
    int deleteCourse(int id);

    /**
     * @param id 传入用户参数
     * @return 获取用户拥有的课程集合
     */
    List<Course> userCourse(int id);
}