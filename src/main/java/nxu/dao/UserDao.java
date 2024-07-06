package nxu.dao;

import nxu.entity.User;

import java.util.List;

/**
 * 用户User的数据库持久层接口 (张宏业)
 * 此处是为了演示原本的JDBC，因此没有使用MyBatis
 */
public interface UserDao {

    /**
     * 查询全部用户
     *
     * @return User实体类集合
     */
    List<User> queryAllUsers();

    /**
     * 用户登录
     *
     * @param account  账号
     * @param password 密码
     * @return 单个User实体类
     */
    User queryUserToLogin(String account, String password);

    /**
     * 用户注册
     *
     * @param user 单个User实体类
     * @return insert后受影响的行数
     */
    int insertUserToRegister(User user);

    /**
     * 删除用户
     *
     * @param id 用户编号(主键)
     * @return delete后受影响的行数
     */
    int deleteUserById(int id);

    /**
     * 更新用户
     *
     * @param user 单个User实体类
     * @return update后受影响的行数
     */
    int updateUser(User user);
}