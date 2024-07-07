package nxu.service;

import nxu.entity.Homework;
import nxu.mapper.HomeworkMapper;
import nxu.utils.MybatisUtil;

import java.util.List;
import java.util.Map;

/**
 * 作业服务层接口实现类 (张宏业)
 */
public class HomeworkServiceImpl implements HomeworkService {

    /**
     * 通过作业编号查询单个作业信息
     *
     * @param id 作业编号编号
     * @return 单个作业实体
     */
    @Override
    public Homework getHomeworkById(int id) {
        return MybatisUtil.getSqlSession().getMapper(HomeworkMapper.class).getHomeworkById(id);
    }

    /**
     * 通过课程编号或者用户编号查询作业
     *
     * @param courseId 课程编号，0 表示 null
     * @param userId   用户编号，0 表示 null
     * @return 作业实体集合
     */
    @Override
    public List<Homework> getAllHomework(int courseId, int userId) {
        return MybatisUtil.getSqlSession().getMapper(HomeworkMapper.class).getAllHomework(courseId, userId);
    }

    /**
     * 添加作业
     *
     * @param homework 作业实体类
     * @return 返回1-添加成功，返回0-添加失败
     */
    @Override
    public int insertHomework(Homework homework) {
        return MybatisUtil.getSqlSession().getMapper(HomeworkMapper.class).insertHomework(homework);
    }

    /**
     * 修改作业
     *
     * @param map 作业参数map(title、info、dateline、again)
     * @return
     */
    @Override
    public int updateHomework(Map<String, Object> map) {
        return MybatisUtil.getSqlSession().getMapper(HomeworkMapper.class).updateHomework(map);
    }

    /**
     * 批量删除作业(支持删除单个作业)
     *
     * @param idList 作业编号集合
     * @return 返回0-删除失败，返回其他-删除成功的数目
     */
    @Override
    public int deleteHomeworkById(List<Integer> idList) {
        return MybatisUtil.getSqlSession().getMapper(HomeworkMapper.class).deleteHomeworkById(idList);
    }
}