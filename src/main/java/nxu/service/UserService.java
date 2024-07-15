package nxu.service;

import nxu.entity.User;

import java.util.List;
import java.util.Map;

/**
 * 用户User的服务层接口 (张宏业)
 */
public interface UserService {

    /**
     * 查询全部用户
     *
     * @param map 查询参数的map(name、phone、gender、type)
     * @return User实体类集合
     */
    List<User> queryAllUsers(Map<String, Object> map);

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

    /**
     * 通过用户编号获取用户信息
     *
     * @param id 用户编号
     * @return 用户实体类
     */
    User queryUserById(int id);

    /**
     * 判断用户注册的手机号是否存在
     *
     * @param phone 手机号码
     * @return 返回0-手机号未注册，返回1-手机号已注册
     */
    int isUserExist(String phone);
}