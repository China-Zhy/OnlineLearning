package nxu.mapper;

import nxu.entity.Active;

import java.util.List;
import java.util.Map;

/**
 * 活动数据层接口 (胡昊)
 */

public interface ActiveMapper {
    /**
     * 通过活动编号查询活动信息
     *
     * @param id 活动编号
     * @return 单个活动实体
     */
    Active getActiveById(int id);

    /**
     * 通过指定条件查询多个活动实体
     *
     * @param map 条件参数
     * @return 活动实体集合
     */
    List<Active> getAllActive(Map<String, Object> map);

    /**
     * 添加一个活动
     *
     * @param active 活动实体类
     * @return 返回1-添加成功，返回0-添加失败
     */
    int insertActive(Active active);

    /**
     * 删除一个活动
     *
     * @param id 活动实体类
     * @return 返回1-删除成功，返回0-删除失败
     */
    int deleteActiveById(int id);

    /**
     * 修改一个活动
     *
     * @param map 活动实体类
     * @return 返回1-修改成功，返回0-修改失败
     */
    int updateActive(Map<String, Object> map);
}