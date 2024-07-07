package nxu.service;

import nxu.entity.Active;
import nxu.mapper.ActiveMapper;
import nxu.utils.MybatisUtil;

import java.util.List;
import java.util.Map;

/**
 * 活动服务层接口实现类 (胡昊)
 */
public class ActiveServiceImpl implements ActiveService {

    /**
     * 通过活动编号查询活动信息
     *
     * @param id 活动编号
     * @return 单个活动实体
     */
    @Override
    public Active getActiveById(int id) {
        return MybatisUtil.getSqlSession().getMapper(ActiveMapper.class).getActiveById(id);
    }

    /**
     * 通过指定条件查询多个活动实体
     *
     * @param map 条件参数
     * @return 活动实体集合
     */
    @Override
    public List<Active> getAllActive(Map<String, Object> map) {
        return MybatisUtil.getSqlSession().getMapper(ActiveMapper.class).getAllActive(map);
    }

    /**
     * 添加一个活动
     *
     * @param active 活动实体类
     * @return 返回1-添加成功，返回0-添加失败
     */
    @Override
    public int insertActive(Active active) {
        return MybatisUtil.getSqlSession().getMapper(ActiveMapper.class).insertActive(active);
    }

    /**
     * 删除一个活动
     *
     * @param id 活动实体类
     * @return 返回1-删除成功，返回0-删除失败
     */
    @Override
    public int deleteActiveById(int id) {
        return MybatisUtil.getSqlSession().getMapper(ActiveMapper.class).deleteActiveById(id);
    }

    /**
     * 修改一个活动
     *
     * @param map 活动实体类
     * @return 返回1-修改成功，返回0-修改失败
     */
    @Override
    public int updateActive(Map<String, Object> map) {
        return MybatisUtil.getSqlSession().getMapper(ActiveMapper.class).updateActive(map);
    }
}