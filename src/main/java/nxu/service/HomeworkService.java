package nxu.service;

import com.github.pagehelper.PageInfo;
import nxu.entity.Homework;

import java.util.List;
import java.util.Map;

/**
 * 作业服务层接口 (张宏业)
 */
public interface HomeworkService {

    /**
     * 通过作业编号查询单个作业信息
     *
     * @param id 作业编号编号
     * @return 单个作业实体
     */
    Homework getHomeworkById(int id);

    /**
     * 多条件查询作业
     *
     * @param map 查询条件集合(title、courseId、userId、create、dateline、again)
     * @return 作业集合
     */
    PageInfo<Homework> getAllHomework(Map<String, Object> map);

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
     * @param map 作业参数map(title、info、dateline、again)
     * @return 返回1-修改成功，返回0-修改失败
     */
    int updateHomework(Map<String, Object> map);

    /**
     * 批量删除作业(支持删除单个作业)
     *
     * @param idList 作业编号集合
     * @return 返回0-删除失败，返回其他-删除成功的数目
     */
    int deleteHomeworkById(List<Integer> idList);
}