package nxu.mapper;

import nxu.entity.CourseType;

import java.util.List;
import java.util.Map;

/**
 * 课程类型数据接口 (樊雪儿)
 */
public interface CourseTypeMapper {

    /**
     * 新增课程类型
     *
     * @param courseType 课程类型实体
     * @return 返回受影响参数
     */
    int insertCourseType(CourseType courseType);

    /**
     * 删除课程类型
     *
     * @param id 课程编号
     * @return 返回受影响参数
     */
    int deleteCourseType(int id);

    /**
     *
     * @param map 传入实体参数 (id, pageIndex, pageSize)
     * @return 返回课程类型的集合
     */
    List<CourseType> getCourseType(Map<String,Object> map);
}
