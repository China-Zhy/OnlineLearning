package nxu.mapper;

import nxu.entity.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * 角色数据层接口[全注解形式] (张宏业)
 */
public interface RoleMapper {

    /**
     * 通过角色编号获取角色信息
     *
     * @param id 角色编号
     * @return 角色实体
     */
    @Select("select * from `role` where id = #{id}")
    Role getRoleById(@Param("id") int id);

    /**
     * 获取全部角色信息
     *
     * @return 角色实体集合
     */
    @Select("select * from `role`")
    List<Role> getAllRoles();

    /**
     * 添加一个角色信息(目前禁用)
     *
     * @param role 角色实体
     * @return 返回1-添加成功，返回0-添加失败
     */
    @Insert("insert into `role` (id,identity) values (null, #{identity})")
    int insertRole(Role role);

    /**
     * 修改角色信息(目前禁用)
     *
     * @param map 参数map(id、identity)
     * @return 返回1-修改成功，返回0-修改失败
     */
    @Update("update `role` set identity = #{identity} where id = #{id}")
    int updateRole(Map<String, Object> map);

    /**
     * 删除一个角色信息(目前禁用)
     *
     * @param id 角色编号
     * @return 返回1-删除成功，返回0-删除失败
     */
    @Delete("delete from `role` where id = #{id}")
    int deleteRole(@Param("id") int id);

}