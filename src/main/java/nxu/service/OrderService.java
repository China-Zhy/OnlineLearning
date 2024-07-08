package nxu.service;

import nxu.entity.Order;

import java.util.List;
import java.util.Map;

/**
 * 订单服务层接口 (胡昊)
 */
public interface OrderService {

    /**
     * 通过订单编号查询订单信息
     *
     * @param id 订单编号
     * @return 单个订单实体
     */
    Order getOrderById(int id);

    /**
     * 通过指定条件查询多个订单实体
     *
     * @param map 条件参数
     * @return 订单实体集合
     */
    List<Order> getAllOrder(Map<String, Object> map);

    /**
     * 添加一个订单
     *
     * @param order 订单实体类
     * @return 返回1-添加成功，返回0-添加失败
     */
    int insertOrder(Order order);

    /**
     * 删除一个订单
     *
     * @param id 订单实体类
     * @return 返回1-删除成功，返回0-删除失败
     */
    int deleteOrderById(int id);
}