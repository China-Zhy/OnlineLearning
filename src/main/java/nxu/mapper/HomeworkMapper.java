package nxu.mapper;

import nxu.entity.Homework;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 作业数据层接口 (张宏业)
 */
public interface HomeworkMapper {

    /**
     * 通过作业编号查询单个作业信息
     *
     * @param id 作业编号编号
     * @return 单个作业实体
     */
    Homework getHomeworkById(int id);

    /**
     * 通过课程编号或者用户编号查询作业
     *
     * @param courseId 课程编号，0 表示 null
     * @param userId   用户编号，0 表示 null
     * @return 作业实体集合
     */
    List<Homework> getAllHomework(@Param("courseId") int courseId, @Param("userId") int userId);

    /**
     * 添加作业
     *
     * @param homework 作业实体类
     * @return 返回1-添加成功，返回0-添加失败
     */
    int insertHomework(Homework homework);

    /**
     * 修改作业
     *
     * @param map 作业参数map(id、title、info、dateline、again)
     * @return
     */
    int updateHomework(Map<String, Object> map);

    /**
     * 批量删除作业(支持删除单个作业)
     *
     * @param idList 作业编号集合
     * @return 返回0-删除失败，返回其他-删除成功的数目
     */
    int deleteHomeworkById(@Param("idList") List<Integer> idList);
}