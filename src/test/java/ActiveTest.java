import com.github.pagehelper.PageInfo;
import nxu.entity.Active;
import nxu.service.ActiveService;
import nxu.service.impl.ActiveServiceImpl;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;

/**
 * 活动相关功能测试 (胡昊)
 */
public class ActiveTest {

    private static final ActiveService activeService = new ActiveServiceImpl();

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
        map.put("title", "活");
        map.put("userId", 4);
        map.put("create", "2024-06-06");
        map.put("pageIndex", 1);
        map.put("pageSize", 4);
        PageInfo<Active> allActive = activeService.getAllActive(map);
        for (Active active : allActive.getList()) {
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
        map.put("discount", '0');
        int i = activeService.updateActive(map);
        System.out.println(i);
    }
}