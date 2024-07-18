package nxu.service.impl;

import nxu.entity.Role;
import nxu.mapper.RoleMapper;
import nxu.service.RoleService;
import nxu.utils.MybatisUtil;

import java.util.List;
import java.util.Map;

/**
 * 角色服务层接口实现类 (张宏业)
 */
public class RoleServiceImpl implements RoleService {

    /**
     * 通过角色编号获取角色信息
     *
     * @param id 角色编号
     * @return 角色实体
     */
    @Override
    public Role getRoleById(int id) {
        Role role = MybatisUtil.getSqlSession().getMapper(RoleMapper.class).getRoleById(id);
        MybatisUtil.getSqlSession().close();
        return role;
    }

    /**
     * 获取全部角色信息
     *
     * @param pageIndex 当前页码
     * @param pageSize  每页数据量
     * @return 角色实体集合(通过SQL语句进行分页)
     */
    @Override
    public List<Role> getAllRoles(int pageIndex, int pageSize) {
        int beginIndex, endIndex;
        if (pageIndex > 0 && pageSize > 0) {
            beginIndex = (pageIndex - 1) * pageSize;
            endIndex = (pageIndex * pageSize);
        } else {
            System.out.println("\n【非法的分页参数】\n");
            return null;
        }
        List<Role> allRoles = MybatisUtil.getSqlSession().getMapper(RoleMapper.class).getAllRoles(beginIndex, endIndex);
        MybatisUtil.getSqlSession().close();
        return allRoles;
    }

    /**
     * 添加一个角色信息(目前禁用)
     *
     * @param role 角色实体
     * @return 返回1-添加成功，返回0-添加失败
     */
    @Override
    public int insertRole(Role role) {
        int i = MybatisUtil.getSqlSession().getMapper(RoleMapper.class).insertRole(role);
        MybatisUtil.getSqlSession().close();
        return i;
    }

    /**
     * 修改角色信息(目前禁用)
     *
     * @param map 参数map(id、identity)
     * @return 返回1-修改成功，返回0-修改失败
     */
    @Override
    public int updateRole(Map<String, Object> map) {
        int i = MybatisUtil.getSqlSession().getMapper(RoleMapper.class).updateRole(map);
        MybatisUtil.getSqlSession().close();
        return i;
    }

    /**
     * 删除一个角色信息(目前禁用)
     *
     * @param id 角色编号
     * @return 返回1-删除成功，返回0-删除失败
     */
    @Override
    public int deleteRole(int id) {
        int i = MybatisUtil.getSqlSession().getMapper(RoleMapper.class).deleteRole(id);
        MybatisUtil.getSqlSession().close();
        return i;
    }
}