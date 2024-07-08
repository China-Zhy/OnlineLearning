package nxu.service;

import nxu.entity.CourseType;
import nxu.mapper.CourseTypeMapper;
import nxu.utils.MybatisUtil;

/**
 * 课程类型服务层接口实现 (樊雪儿)
 */
public class CourseTypeServiceImpl implements CourseTypeService {

    /**
     * 新增课程类型
     *
     * @param courseType 课程类型实体
     * @return 返回1-添加成功，返回0-添加失败
     */
    @Override
    public int insertCourseType(CourseType courseType) {
        return MybatisUtil.getSqlSession().getMapper(CourseTypeMapper.class).insertCourseType(courseType);
    }

    /**
     * 删除课程类型
     *
     * @param id 课程编号
     * @return 返回1-删除成功，返回0-删除失败
     */
    @Override
    public int deleteCourseType(int id) {
        return MybatisUtil.getSqlSession().getMapper(CourseTypeMapper.class).deleteCourseType(id);
    }

    /**
     * 获取课程类型
     *
     * @param id 课程编号
     * @return 课程类型名
     */
    @Override
    public String getCourseType(int id) {
        return MybatisUtil.getSqlSession().getMapper(CourseTypeMapper.class).getCourseType(id);
    }
}