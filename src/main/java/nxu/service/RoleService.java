package nxu.service;

import nxu.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * 角色服务层接口 (张宏业)
 */
public interface RoleService {

    /**
     * 通过角色编号获取角色信息
     *
     * @param id 角色编号
     * @return 角色实体
     */
    Role getRoleById(int id);

    /**
     * 获取全部角色信息
     *
     * @param pageIndex 当前页码
     * @param pageSize  每页数据量
     * @return 角色实体集合(通过SQL语句进行分页)
     */
    List<Role> getAllRoles(int pageIndex, int pageSize);

    /**
     * 添加一个角色信息(目前禁用)
     *
     * @param role 角色实体
     * @return 返回1-添加成功，返回0-添加失败
     */
    int insertRole(Role role);

    /**
     * 修改角色信息(目前禁用)
     *
     * @param map 参数map(id、identity)
     * @return 返回1-修改成功，返回0-修改失败
     */
    int updateRole(Map<String, Object> map);

    /**
     * 删除一个角色信息(目前禁用)
     *
     * @param id 角色编号
     * @return 返回1-删除成功，返回0-删除失败
     */
    int deleteRole(int id);

}