package nxu.dao;

import nxu.entity.User;
import nxu.utils.MysqlUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用户User的数据库持久层接口的实现类 (张宏业)
 * 此处是为了演示原本的JDBC，因此没有使用MyBatis(感受原本JDBC的操作，进一步理解MyBatis是如何进行封装)
 */
public class UserDaoImpl implements UserDao {

    /**
     * 查询全部用户
     *
     * @param map 查询参数的map(name、phone、gender、type)
     * @return User实体类集合
     */
    public List<User> queryAllUsers(Map<String, Object> map) {
        List<User> users = new ArrayList<>();
        try {
            Connection connection = MysqlUtil.getConnection();

            StringBuilder builder = new StringBuilder(); // 用于拼接SQL语句
            builder.append("SELECT * FROM `user` where 1=1 ");

            // 此处使用JDBC模拟动态SQL，不过根据业务，参数固定为(name、phone、gender、type)
            if (!map.isEmpty()) {
                if (map.containsKey("name")) {
                    String name = (String) map.get("name");
                    builder.append(" and name like '%").append(name).append("%'");  // 姓名模糊查询
                }
                if (map.containsKey("phone")) {
                    String phone = (String) map.get("phone");
                    builder.append(" and phone = '").append(phone).append("'");
                }
                if (map.containsKey("gender")) {
                    int gender = (int) map.get("gender");
                    builder.append(" and gender = ").append(gender);
                }
                if (map.containsKey("type")) {
                    int type = (int) map.get("type");
                    builder.append(" and type = ").append(type);
                }
            }

            PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setGender(resultSet.getInt("gender"));
                user.setPhone(resultSet.getString("phone"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setImage(resultSet.getString("image"));
                user.setRegister(resultSet.getDate("register"));
                user.setScore(resultSet.getInt("score"));
                user.setType(resultSet.getInt("type"));
                user.setInfo(resultSet.getString("info"));
                user.setState(resultSet.getInt("state"));
                users.add(user);
            }
            MysqlUtil.toFreeResource(connection, preparedStatement, resultSet);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("【" + this.getClass() + "这里捕获到了异常】");
            throw new RuntimeException(e);
        }
        return users;
    }

    /**
     * 用户登录
     *
     * @param account  账号(手机号/密码)
     * @param password 密码
     * @return 单个User实体类
     */
    @Override
    public User queryUserToLogin(String account, String password) {
        User user = null;
        try {
            Connection connection = MysqlUtil.getConnection();
            PreparedStatement ps;
            // 此处可使用phone或email登录，只要有一个与之匹配即登录成功(因为数据库中phone和email都是unique)
            if (account.contains("@")) {
                ps = connection.prepareStatement("SELECT * FROM `user` WHERE password=? AND email=?;");
            } else {
                ps = connection.prepareStatement("SELECT * FROM `user` WHERE password=? AND phone=?;");
            }
            ps.setString(1, password);
            ps.setString(2, account);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setGender(resultSet.getInt("gender"));
                user.setPhone(resultSet.getString("phone"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setImage(resultSet.getString("image"));
                user.setRegister(resultSet.getDate("register"));
                user.setScore(resultSet.getInt("score"));
                user.setType(resultSet.getInt("type"));
                user.setInfo(resultSet.getString("info"));
                user.setState(resultSet.getInt("state"));
            }
            MysqlUtil.toFreeResource(connection, ps, resultSet);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("【" + this.getClass() + "这里捕获到了异常】");
            throw new RuntimeException(e);
        }
        return user;
    }

    /**
     * 用户注册
     *
     * @param user 单个User实体类
     * @return insert后受影响的行数
     */
    @Override
    public int insertUserToRegister(User user) {
        int result = 0;
        try {
            Connection connection = MysqlUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `user` (name, gender, phone, email, password, image, register, type, info, state) VALUES (?, ?, ?, ?, ?, ?, NOW(), ?, ?, ?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getGender());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getImage());
            preparedStatement.setInt(7, user.getType());
            preparedStatement.setString(8, user.getInfo());
            preparedStatement.setInt(9, user.getState());

            result = preparedStatement.executeUpdate();
            MysqlUtil.toFreeResource(connection, preparedStatement, null);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("【" + this.getClass() + "这里捕获到了异常】");
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 删除用户
     *
     * @param id 用户编号(主键)
     * @return delete后受影响的行数
     */
    @Override
    public int deleteUserById(int id) {
        int result = 0;
        try {
            Connection connection = MysqlUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM `user` WHERE id=?");
            preparedStatement.setInt(1, id);

            result = preparedStatement.executeUpdate();
            MysqlUtil.toFreeResource(connection, preparedStatement, null);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("【" + this.getClass() + "这里捕获到了异常】");
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 更新用户
     *
     * @param user 单个User实体类
     * @return update后受影响的行数
     */
    @Override
    public int updateUser(User user) {
        int result = 0;
        try {
            Connection connection = MysqlUtil.getConnection();
            PreparedStatement preparedStatement;
            if (user.getState() == 0) {
                preparedStatement = connection.prepareStatement("UPDATE `user` SET score=score+? WHERE id=?");
                preparedStatement.setInt(1, user.getScore());
                preparedStatement.setInt(2, user.getId());
            } else {
                preparedStatement = connection.prepareStatement("UPDATE `user` SET name=?,gender=?,phone=?,email=?,password=?,image=?,type=?,info=?,state=?,score=? WHERE id=?");
                preparedStatement.setString(1, user.getName());
                preparedStatement.setInt(2, user.getGender());
                preparedStatement.setString(3, user.getPhone());
                preparedStatement.setString(4, user.getEmail());
                preparedStatement.setString(5, user.getPassword());
                preparedStatement.setString(6, user.getImage());
                preparedStatement.setInt(7, user.getType());
                preparedStatement.setString(8, user.getInfo());
                preparedStatement.setInt(9, user.getState());
                preparedStatement.setInt(10, user.getScore());
                preparedStatement.setInt(11, user.getId());
            }

            result = preparedStatement.executeUpdate();
            MysqlUtil.toFreeResource(connection, preparedStatement, null);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("【" + this.getClass() + "这里捕获到了异常】");
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 通过用户编号获取用户信息
     *
     * @param id 用户编号
     * @return 用户实体类
     */
    @Override
    public User queryUserById(int id) {
        User user = null;
        try {
            Connection connection = MysqlUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `user` WHERE id=?;");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setGender(resultSet.getInt("gender"));
                user.setPhone(resultSet.getString("phone"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setImage(resultSet.getString("image"));
                user.setRegister(resultSet.getDate("register"));
                user.setScore(resultSet.getInt("score"));
                user.setType(resultSet.getInt("type"));
                user.setInfo(resultSet.getString("info"));
                user.setState(resultSet.getInt("state"));
            }
            MysqlUtil.toFreeResource(connection, preparedStatement, resultSet);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("【" + this.getClass() + "这里捕获到了异常】");
            throw new RuntimeException(e);
        }
        return user;
    }

    /**
     * 判断用户注册的手机号是否存在
     *
     * @param phone 手机号码
     * @return 返回0-手机号未注册，返回1-手机号已注册
     */
    @Override
    public int isUserExist(String phone) {
        int count = -1; // 某手机号数量
        try {
            Connection connection = MysqlUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) count FROM `user` WHERE phone=?;");
            preparedStatement.setString(1, phone);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                count = resultSet.getInt("count");
            }
            MysqlUtil.toFreeResource(connection, preparedStatement, resultSet);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("【" + this.getClass() + "这里捕获到了异常】");
            throw new RuntimeException(e);
        }
        return count;
    }
}