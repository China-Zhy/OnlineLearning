package nxu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import nxu.entity.Order;
import nxu.mapper.OrderMapper;
import nxu.service.OrderService;
import nxu.utils.MybatisUtil;

import java.util.List;
import java.util.Map;

/**
 * 订单服务层接口实现类 (胡昊)
 */
public class OrderServiceImpl implements OrderService {

    /**
     * 通过订单编号查询订单信息
     *
     * @param id 订单编号
     * @return 单个订单实体
     */
    @Override
    public Order getOrderById(int id) {
        Order order = MybatisUtil.getSqlSession().getMapper(OrderMapper.class).getOrderById(id);
        MybatisUtil.getSqlSession().close();
        return order;
    }

    /**
     * 通过指定条件查询多个订单实体
     *
     * @param map 条件参数(userId,courseId,time,state)
     * @return 订单实体集合
     */
    @Override
    public PageInfo<Order> getAllOrder(Map<String, Object> map) {
        PageHelper.startPage((int) map.get("pageIndex"), (int) map.get("pageSize"));
        List<Order> order = MybatisUtil.getSqlSession().getMapper(OrderMapper.class).getAllOrder(map);
        MybatisUtil.getSqlSession().close();
        return new PageInfo<>(order);
    }


    /**
     * 添加一个订单
     *
     * @param order 订单实体类
     * @return 返回1-添加成功，返回0-添加失败
     */
    @Override
    public int insertOrder(Order order) {
        int i = MybatisUtil.getSqlSession().getMapper(OrderMapper.class).insertOrder(order);
        MybatisUtil.getSqlSession().close();
        return i;
    }

    /**
     * 删除一个订单
     *
     * @param id 订单实体类
     * @return 返回1-删除成功，返回0-删除失败
     */
    @Override
    public int deleteOrderById(int id) {
        int i = MybatisUtil.getSqlSession().getMapper(OrderMapper.class).deleteOrderById(id);
        MybatisUtil.getSqlSession().close();
        return i;
    }
}