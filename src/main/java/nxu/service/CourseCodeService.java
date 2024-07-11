package nxu.service;

import com.github.pagehelper.PageInfo;
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
     * 获取课程邀请码信息
     * @param map 实体参数(userId, courseId)
     * @return 返回课程邀请码的集合
     */
    PageInfo<CourseCode> getCourseCode(Map<String, Object> map);
}
