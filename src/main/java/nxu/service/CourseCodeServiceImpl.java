package nxu.service;

import nxu.entity.CourseCode;
import nxu.mapper.CourseCodeMapper;
import nxu.utils.MybatisUtil;

import java.util.Map;

/**
 * 课程邀请码数据接口实现 (樊雪儿)
 */
public class CourseCodeServiceImpl implements CourseCodeService {

    /**
     * 增加课程邀请码
     *
     * @param courseCode 课程邀请码实体
     * @return 受影响行数
     */
    @Override
    public int insertCourseCode(CourseCode courseCode) {
        return MybatisUtil.getSqlSession().getMapper(CourseCodeMapper.class).insertCourseCode(courseCode);
    }

    /**
     * 删除课程邀请码
     *
     * @param map 实体参数(userid, courseId)
     * @return 受影响行数
     */
    @Override
    public int deleteCourseCode(Map<String, Object> map) {
        return MybatisUtil.getSqlSession().getMapper(CourseCodeMapper.class).deleteCourseCode(map);
    }

    /**
     * 根据用户id和课程id获取课程邀请码
     *
     * @param map 实体参数(userid, courseId)
     * @return 邀请码具体内容
     */
    @Override
    public String getCourseCode(Map<String, Object> map) {
        return MybatisUtil.getSqlSession().getMapper(CourseCodeMapper.class).getCourseCode(map);
    }
}
