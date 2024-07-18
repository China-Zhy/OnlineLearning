import nxu.entity.Role;
import nxu.service.RoleService;
import nxu.service.impl.RoleServiceImpl;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

/**
 * 角色相关功能测试 (张宏业)
 */
public class RoleTest {

    private static final RoleService roleService = new RoleServiceImpl();

    @Test
    public void test1() {
        Role role = roleService.getRoleById(1);
        System.out.println(role);
    }

    @Test
    public void test2() {
        List<Role> allRoles = roleService.getAllRoles(2, 2);
        for (Role role : allRoles) {
            System.out.println(role);
        }
    }

    @Test
    public void test3() {
        int i = roleService.insertRole(new Role(0, "测试角色"));
        System.out.println(i);
    }

    @Test
    public void test4() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", 5);
        map.put("identity", "修改后的角色");
        int i = roleService.updateRole(map);
        System.out.println(i);
    }

    @Test
    public void test5() {
        int i = roleService.deleteRole(5);
        System.out.println(i);
    }
}