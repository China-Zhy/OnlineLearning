package nxu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import nxu.entity.CourseType;
import nxu.mapper.CourseTypeMapper;
import nxu.service.CourseTypeService;
import nxu.utils.MybatisUtil;

import java.util.List;
import java.util.Map;

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
     * @param map 传入实体参数 (id, pageIndex, pageSize)
     * @return 返回课程类型的集合
     */
    @Override
    public PageInfo<CourseType> getCourseType(Map<String, Object> map) {
        PageHelper.startPage((int) (map.get("pageIndex")), (int) map.get("pageSize"));
        List<CourseType> list = MybatisUtil.getSqlSession().getMapper(CourseTypeMapper.class).getCourseType(map);
        return new PageInfo<>(list);
    }


}