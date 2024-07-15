package nxu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import nxu.entity.Course;
import nxu.mapper.CourseMapper;
import nxu.mapper.OwnerShipMapper;
import nxu.service.CourseService;
import nxu.utils.MybatisUtil;

import java.util.List;
import java.util.Map;

/**
 * 文件服务层接口实现 (樊雪儿、张宏业)
 */
public class CourseServiceImpl implements CourseService {

    /**
     * 增添课程信息
     *
     * @param course 课程实体类
     * @return insert后受影响行数
     */
    @Override
    public int insertCourse(Course course) {
        return MybatisUtil.getSqlSession().getMapper(CourseMapper.class).insertCourse(course);
    }

    /**
     * 获取搜索的课程集合
     *
     * @param map 实体参数(id, name, course_type, score, pageIndex, pageSize)
     * @return 返回查询的课程实体集合并按照分页长度控制每页输出量
     */
    @Override
    public PageInfo<Course> getCourse(Map<String, Object> map) {
        PageHelper.startPage((int) (map.get("pageIndex")), (int) map.get("pageSize"));
        List<Course> list = MybatisUtil.getSqlSession().getMapper(CourseMapper.class).getCourse(map);
        return new PageInfo<>(list);
    }

    /**
     * 更新课程内容
     *
     * @param map 实体参数 (id, name, image, course_type, info, score, state)
     * @return 更改后受影响的行数
     */
    @Override
    public int updateCourse(Map<String, Object> map) {
        return MybatisUtil.getSqlSession().getMapper(CourseMapper.class).updateCourse(map);
    }

    /**
     * 删除相应课程
     *
     * @param id 课程逐渐
     * @return 删除后受影响的行数
     */
    @Override
    public int deleteCourse(int id) {
        return MybatisUtil.getSqlSession().getMapper(CourseMapper.class).deleteCourse(id);
    }

    /**
     * @param id 传入用户参数
     * @return 获取用户拥有的课程集合
     */
    @Override
    public PageInfo<Course> userCourse(int id) {
        PageHelper.startPage(1, 10);
        List<Course> list = MybatisUtil.getSqlSession().getMapper(CourseMapper.class).userCourse(id);
        return new PageInfo<>(list);
    }

    /**
     * 添加用户购买的课程
     *
     * @param userId   用户编号
     * @param courseId 课程编号
     * @return 返回1-添加成功，返回0-添加失败
     */
    @Override
    public int insertData(int userId, int courseId) {
        return MybatisUtil.getSqlSession().getMapper(OwnerShipMapper.class).insertData(userId, courseId);
    }

    /**
     * 通过用户编号获取用户拥有的课程信息
     *
     * @param map 用户编号、pageIndex、pageSize
     * @return 课程信息集合
     */
    @Override
    public PageInfo<Course> getMyCourse(Map<String, Object> map) {
        PageHelper.startPage((int) map.get("pageIndex"), (int) map.get("pageSize"));
        List<Course> course = MybatisUtil.getSqlSession().getMapper(OwnerShipMapper.class).getMyCourse((int) map.get("userId"));
        return new PageInfo<>(course);
    }

    /**
     * 判断某个用户是否拥有某个课程
     *
     * @param userId   用户编号
     * @param courseId 课程编号
     * @return 返回0-为拥有，返回1-已拥有
     */
    @Override
    public int isUserHaveThisCourse(int userId, int courseId) {
        return MybatisUtil.getSqlSession().getMapper(OwnerShipMapper.class).isUserHaveThisCourse(userId, courseId);
    }

}