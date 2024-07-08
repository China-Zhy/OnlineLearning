package nxu.mapper;

import nxu.entity.CourseType;

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
     * 获取课程类型
     *
     * @param id 课程编号
     * @return 返回课程类型名
     */
    String getCourseType(int id);
}
