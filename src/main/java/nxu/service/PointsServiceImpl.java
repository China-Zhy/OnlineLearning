package nxu.service;

import nxu.entity.Points;
import nxu.mapper.PointsMapper;
import nxu.utils.MybatisUtil;

import java.util.List;
import java.util.Map;

/**
 * 积分服务层接口实现类 (胡昊)
 */
public class PointsServiceImpl implements PointsService {

    /**
     * 通过积分编号查询文件信息
     *
     * @param id 积分编号
     * @return 单个积分实体
     */
    @Override
    public Points getPointsById(int id) {
        return MybatisUtil.getSqlSession().getMapper(PointsMapper.class).getPointsById(id);
    }

    /**
     * 通过指定条件查询多个积分实体
     *
     * @param map 条件参数
     * @return 积分实体集合
     */
    @Override
    public List<Points> getAllPoints(Map<String, Object> map) {
        return MybatisUtil.getSqlSession().getMapper(PointsMapper.class).getAllPoints(map);
    }

    /**
     * 添加一个积分
     *
     * @param points 积分实体类
     * @return 返回1-添加成功，返回0-添加失败
     */
    @Override
    public int insertPoints(Points points) {
        return MybatisUtil.getSqlSession().getMapper(PointsMapper.class).insertPoints(points);
    }

    /**
     * 删除一个活动
     *
     * @param id 活动实体类
     * @return 返回1-删除成功，返回0-删除失败
     */
    @Override
    public int deletePointsById(int id) {
        return MybatisUtil.getSqlSession().getMapper(PointsMapper.class).deletePointsById(id);
    }

    /**
     * 修改一个积分
     *
     * @param map 积分实体类
     * @return 返回1-修改成功，返回0-修改失败
     */
    @Override
    public int updatePoints(Map<String, Object> map) {
        return MybatisUtil.getSqlSession().getMapper(PointsMapper.class).updatePoints(map);
    }
}