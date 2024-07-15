import nxu.entity.User;
import nxu.service.UserService;
import nxu.service.impl.UserServiceImpl;
import org.junit.Test;

import java.util.Date;
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
        map.put("gender", 1);
        map.put("type", 1);
        List<User> users = userService.queryAllUsers(map);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setState(0);
        user.setId(1);
        user.setScore(999);
        int i = userService.updateUser(user);
        System.out.println(i);
    }

    @Test
    public void deleteUser() {
        int result = userService.insertUserToRegister(new User(0, "name", 1, "18898765656", "email@qq.com", "123456", "../../layuiadmin/resource/image/portrait1.png", new Date(), 0, 4, "sb", 2));
        System.out.println(result);
    }
}