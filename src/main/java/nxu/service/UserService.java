package nxu.service;

import nxu.entity.User;

import java.util.List;

/**
 * 用户User的服务层接口 (张宏业)
 */
public interface UserService {
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
    User queryUserByLogin(String account, String password);

    /**
     * 用户注册
     *
     * @param user 单个User实体类
     * @return insert后受影响的行数
     */
    int insertUserByRegister(User user);

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