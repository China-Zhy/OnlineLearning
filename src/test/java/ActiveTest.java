import nxu.entity.Active;
import nxu.service.ActiveService;
import nxu.service.ActiveServiceImpl;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 活动相关功能测试 (胡昊)
 */
public class ActiveTest {

    ActiveService activeService = new ActiveServiceImpl();

    /**
     * 查找测试
     */
    @Test
    public void test1() {
        Active activeById = activeService.getActiveById(1);
        System.out.println(activeById);
    }

    @Test
    public void text2() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("discount", '1');
        List<Active> allActive = activeService.getAllActive(map);
        for (Active active : allActive) {
            System.out.println(active);
        }
    }

    /**
     * 添加测试
     */
    @Test
    public void test3() {
        Date date = new Date("2024/06/06 10:43:35");
        Active active = new Active(3, "活动", "只是一个活动", "/image/hd-002.png", 0, date, date, 4);
        int i = activeService.insertActive(active);
        System.out.println(i);
    }

    /**
     * 删除测试
     */
    @Test
    public void test4() {
        int i = activeService.deleteActiveById(4);
        System.out.println(i);
    }

    /**
     * 更改测试
     */
    @Test
    public void test5() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", 3);
        map.put("info", "只是一个更改4444");
        int i = activeService.updateActive(map);
        System.out.println(i);
    }
}