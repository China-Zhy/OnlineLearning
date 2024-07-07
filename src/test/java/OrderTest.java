import nxu.entity.Order;
import nxu.service.OrderService;
import nxu.service.OrderServiceImpl;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

/**
 * 订单相关功能测试 (胡昊)
 */
public class OrderTest {
    OrderService orderService = new OrderServiceImpl();


    /**
     * 查找测试
     */
    @Test
    public void test1() {
        Order orderById = orderService.getOrderById(1);
        System.out.println(orderById);
    }

    @Test
    public void test2() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", 4);
        map.put("courseId", 1);
        List<Order> allOrder = orderService.getAllOrder(map);
        for (Order order : allOrder) {
            System.out.println(order);
        }
    }

    /**
     * 添加测试
     */
    @Test
    public void test3() {
        Order order = new Order(2, 4, 1, null, 0);
        int i = orderService.insertOrder(order);
        System.out.println(i);
    }

    /**
     * 删除测试
     */
    @Test
    public void test4() {
        int i = orderService.deleteOrderById(2);
        System.out.println(i);
    }
}
