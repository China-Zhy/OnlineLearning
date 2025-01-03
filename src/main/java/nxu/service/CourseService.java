package nxu.service;

import com.github.pagehelper.PageInfo;
import nxu.entity.Course;

import java.util.Map;

/**
 * 课程服务层接口 (樊雪儿、张宏业)
 */
public interface CourseService {

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
     * @param map 实体参数 (id, name, course_type, score, pageIndex, pageSize)
     * @return 返回查询的课程实体集合并按照分页长度控制每页输出量
     */
    PageInfo<Course> getCourse(Map<String, Object> map);

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
     * @param id 课程主键
     * @return 删除后受影响的行数
     */
    int deleteCourse(int id);

    /**
     * @param id 传入用户参数
     * @return 获取用户拥有的课程集合
     */
    PageInfo<Course> userCourse(int id);

    /**
     * 添加用户购买的课程
     *
     * @param userId   用户编号
     * @param courseId 课程编号
     * @return 返回1-添加成功，返回0-添加失败
     */
    int insertData(int userId, int courseId);

    /**
     * 通过用户编号获取用户拥有的课程信息
     *
     * @param map 用户编号、pageIndex、pageSize
     * @return 课程信息集合
     */
    PageInfo<Course> getMyCourse(Map<String, Object> map);

    /**
     * 判断某个用户是否拥有某个课程
     *
     * @param userId   用户编号
     * @param courseId 课程编号
     * @return 返回0-为拥有，返回1-已拥有
     */
    int isUserHaveThisCourse(int userId, int courseId);
}