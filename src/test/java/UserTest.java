import nxu.entity.User;
import nxu.service.UserService;
import nxu.service.UserServiceImpl;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

/**
 * 用户相关功能的测试类 (张宏业)
 */
public class UserTest {

    private final UserService userService = new UserServiceImpl();

    @Test
    public void queryAll() {
        HashMap<String, Object> map = new HashMap<>();
        List<User> users = userService.queryAllUsers(map);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void queryOne() {
        User user = userService.queryUserToLogin("18201521341", "123456");
        System.out.println(user);
    }

    @Test
    public void queryUserWhere() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "张");
        map.put("phone", "18201521341");
        map.put("gender", 0);
        map.put("type", 0);
        List<User> users = userService.queryAllUsers(map);
        for (User user : users) {
            System.out.println(user);
        }
    }

}