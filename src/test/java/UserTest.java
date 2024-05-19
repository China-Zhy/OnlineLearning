import nxu.entity.User;
import nxu.service.UserService;
import nxu.service.UserServiceImpl;
import org.junit.Test;

import java.util.List;

/**
 * 用户相关功能的测试类 (张宏业)
 */
public class UserTest {

    private final UserService userService = new UserServiceImpl();

    @Test
    public void queryAll() {
        List<User> users = userService.queryAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void queryOne() {
        User user = userService.queryUserByLogin("3030695949@qq.com", "123");
        System.out.println(user);
    }

}