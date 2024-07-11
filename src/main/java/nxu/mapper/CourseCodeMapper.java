package nxu.mapper;

import nxu.entity.CourseCode;

import java.util.List;
import java.util.Map;

/**
 * 课程邀请码数据接口 (樊雪儿)
 */
public interface CourseCodeMapper {

    /**
     * 增加课程邀请码
     *
     * @param courseCode 课程邀请码实体
     * @return 受影响行数
     */
    int insertCourseCode(CourseCode courseCode);

    /**
     * 删除课程邀请码
     *
     * @param map 实体参数(userid, courseid)
     * @return 受影响行数
     */
    int deleteCourseCode(Map<String, Object> map);

    /**
     * 根据用户id和课程id获取课程邀请码
     *
     * @param map 实体参数(userid, courseid)
     * @return 邀请码具体内容
     */
    List<CourseCode> getCourseCode(Map<String, Object> map);
}