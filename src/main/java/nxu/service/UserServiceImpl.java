package nxu.service;

import nxu.dao.UserDao;
import nxu.dao.UserDaoImpl;
import nxu.entity.User;

import java.util.List;

/**
 * 用户User的服务层接口的实现类 (张宏业)
 */
public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();

    /**
     * 查询全部用户
     *
     * @return User实体类集合
     */
    @Override
    public List<User> queryAllUsers() {
        return userDao.queryAllUsers();
    }

    /**
     * 用户登录
     *
     * @param account  账号
     * @param password 密码
     * @return 单个User实体类
     */
    @Override
    public User queryUserToLogin(String account, String password) {
        return userDao.queryUserToLogin(account, password);
    }

    /**
     * 用户注册
     *
     * @param user 单个User实体类
     * @return insert后受影响的行数
     */
    @Override
    public int insertUserToRegister(User user) {
        return userDao.insertUserToRegister(user);
    }

    /**
     * 删除用户
     *
     * @param id 用户编号(主键)
     * @return delete后受影响的行数
     */
    @Override
    public int deleteUserById(int id) {
        return userDao.deleteUserById(id);
    }

    /**
     * 更新用户
     *
     * @param user 单个User实体类
     * @return update后受影响的行数
     */
    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }
}