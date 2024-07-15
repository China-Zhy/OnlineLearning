package nxu.mapper;

import nxu.entity.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户购买课程数据层接口 (张宏业)
 */
public interface OwnerShipMapper {

    /**
     * 添加用户购买的课程
     *
     * @param userId   用户编号
     * @param courseId 课程编号
     * @return 返回1-添加成功，返回0-添加失败
     */
    int insertData(@Param("userId") int userId, @Param("courseId") int courseId);

    /**
     * 通过用户编号获取用户拥有的课程信息
     *
     * @param userId 用户编号
     * @return 课程信息集合
     */
    List<Course> getMyCourse(int userId);

    /**
     * 判断某个用户是否拥有某个课程
     *
     * @param userId   用户编号
     * @param courseId 课程编号
     * @return 返回0-为拥有，返回1-已拥有
     */
    int isUserHaveThisCourse(@Param("userId") int userId, @Param("courseId") int courseId);
}