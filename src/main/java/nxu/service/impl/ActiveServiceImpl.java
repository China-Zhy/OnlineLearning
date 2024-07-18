package nxu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import nxu.entity.Active;
import nxu.mapper.ActiveMapper;
import nxu.service.ActiveService;
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
        Active active = MybatisUtil.getSqlSession().getMapper(ActiveMapper.class).getActiveById(id);
        MybatisUtil.getSqlSession().close();
        return active;
    }

    /**
     * 通过指定条件查询多个活动实体
     *
     * @param map 条件参数(discount,create,deadline,userId)
     * @return 活动实体集合
     */
    @Override
    public PageInfo<Active> getAllActive(Map<String, Object> map) {
        PageHelper.startPage((int) map.get("pageIndex"), (int) map.get("pageSize"));
        List<Active> actives = MybatisUtil.getSqlSession().getMapper(ActiveMapper.class).getAllActive(map);
        MybatisUtil.getSqlSession().close();
        return new PageInfo<>(actives);
    }

    /**
     * 添加一个活动
     *
     * @param active 活动实体类
     * @return 返回1-添加成功，返回0-添加失败
     */
    @Override
    public int insertActive(Active active) {
        int i = MybatisUtil.getSqlSession().getMapper(ActiveMapper.class).insertActive(active);
        MybatisUtil.getSqlSession().close();
        return i;
    }

    /**
     * 删除一个活动
     *
     * @param id 活动实体类
     * @return 返回1-删除成功，返回0-删除失败
     */
    @Override
    public int deleteActiveById(int id) {
        int i = MybatisUtil.getSqlSession().getMapper(ActiveMapper.class).deleteActiveById(id);
        MybatisUtil.getSqlSession().close();
        return i;
    }

    /**
     * 修改一个活动
     *
     * @param map 活动实体类
     * @return 返回1-修改成功，返回0-修改失败
     */
    @Override
    public int updateActive(Map<String, Object> map) {
        int i = MybatisUtil.getSqlSession().getMapper(ActiveMapper.class).updateActive(map);
        MybatisUtil.getSqlSession().close();
        return i;
    }
}