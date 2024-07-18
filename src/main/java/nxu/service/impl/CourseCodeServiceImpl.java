package nxu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import nxu.entity.CourseCode;
import nxu.mapper.CourseCodeMapper;
import nxu.service.CourseCodeService;
import nxu.utils.MybatisUtil;

import java.util.List;
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
        int i = MybatisUtil.getSqlSession().getMapper(CourseCodeMapper.class).insertCourseCode(courseCode);
        MybatisUtil.getSqlSession().close();
        return i;
    }

    /**
     * 删除课程邀请码
     *
     * @param map 实体参数(userid, courseId)
     * @return 受影响行数
     */
    @Override
    public int deleteCourseCode(Map<String, Object> map) {
        int i = MybatisUtil.getSqlSession().getMapper(CourseCodeMapper.class).deleteCourseCode(map);
        MybatisUtil.getSqlSession().close();
        return i;
    }

    /**
     * 获取课程邀请码信息
     *
     * @param map 实体参数(userId, courseId)
     * @return 返回课程邀请码的集合
     */
    @Override
    public PageInfo<CourseCode> getCourseCode(Map<String, Object> map) {
        PageHelper.startPage((int) (map.get("pageIndex")), (int) map.get("pageSize"));
        List<CourseCode> list = MybatisUtil.getSqlSession().getMapper(CourseCodeMapper.class).getCourseCode(map);
        MybatisUtil.getSqlSession().close();
        return new PageInfo<>(list);
    }
}