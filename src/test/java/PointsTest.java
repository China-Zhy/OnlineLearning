import com.github.pagehelper.PageInfo;
import nxu.entity.Points;
import nxu.service.PointsService;
import nxu.service.PointsServiceImpl;
import org.junit.Test;

import java.util.HashMap;

/**
 * 积分相关功能测试 (胡昊)
 */
public class PointsTest {
    PointsService pointsService = new PointsServiceImpl();

    /**
     * 查找测试
     */
    @Test
    public void test1() {
        Points pointsById = pointsService.getPointsById(1);
        System.out.println(pointsById);
    }

    @Test
    public void test2() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", 4);
        map.put("time", "2024-06-06");
        map.put("pageIndex", 1);
        map.put("pageSize", 4);
        PageInfo<Points> allPoints = pointsService.getAllPoints(map);
        for (Points points : allPoints.getList()) {
            System.out.println(points);
        }
    }

    /**
     * 添加测试
     */
    @Test
    public void test3() {
        Points points = new Points(2, 4, "用户充值", -10, null);
        int i = pointsService.insertPoints(points);
        System.out.println(i);
    }

    /**
     * 删除测试
     */
    @Test
    public void test4() {
        int i = pointsService.deletePointsById(2);
        System.out.println(i);
    }

    /**
     * 更改测试
     */
    @Test
    public void test5() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", 2);
        map.put("userId",4);
        map.put("type", 2);
        int i = pointsService.updatePoints(map);
        System.out.println(i);
    }
}
