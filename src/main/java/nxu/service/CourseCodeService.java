package nxu.service;

import nxu.entity.CourseCode;

import java.util.Map;

/**
 * 课程邀请码服务层接口 (樊雪儿)
 */
public interface CourseCodeService {

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
     * @param map 实体参数(userid, courseId)
     * @return 受影响行数
     */
    int deleteCourseCode(Map<String, Object> map);

    /**
     * 根据用户id和课程id获取课程邀请码
     *
     * @param map 实体参数(userid, courseId)
     * @return 邀请码具体内容
     */
    String getCourseCode(Map<String, Object> map);
}
