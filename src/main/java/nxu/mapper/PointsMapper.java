package nxu.mapper;


import nxu.entity.Points;

import java.util.List;
import java.util.Map;

/**
 * 积分数据层接口 (胡昊)
 */
public interface PointsMapper {

    /**
     * 通过积分编号查询文件信息
     *
     * @param id 积分编号
     * @return 单个积分实体
     */
    Points getPointsById(int id);

    /**
     * 通过指定条件查询多个积分实体
     *
     * @param map 条件参数
     * @return 积分实体集合
     */
    List<Points> getAllPoints(Map<String, Object> map);

    /**
     * 添加一个积分
     *
     * @param points 积分实体类
     * @return 返回1-添加成功，返回0-添加失败
     */
    int insertPoints(Points points);

    /**
     * 删除一个活动
     *
     * @param id 活动实体类
     * @return 返回1-删除成功，返回0-删除失败
     */
    int deletePointsById(int id);

    /**
     * 修改一个积分
     *
     * @param map 积分实体类
     * @return 返回1-修改成功，返回0-修改失败
     */
    int updatePoints(Map<String, Object> map);
}