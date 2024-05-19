package nxu.dao;

import nxu.entity.Role;
import nxu.entity.User;
import nxu.utils.MysqlTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户User的数据库持久层接口的实现类(JDBC形式) (张宏业)
 */
public class UserDaoImpl implements UserDao {

    /**
     * 查询全部用户
     *
     * @return User实体类集合
     */
    @Override
    public List<User> queryAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Connection connection = MysqlTools.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT user.*,role.identity FROM user,role WHERE user.type=role.id");
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
                Role role = new Role();
                role.setId(resultSet.getInt("type"));
                role.setName(resultSet.getString("identity"));
                user.setRole(role);
                users.add(user);
            }
            MysqlTools.toFreeResource(connection, preparedStatement, resultSet);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("【" + this.getClass() + "这里捕获到了异常】");
            throw new RuntimeException(e);
        }
        return users;
    }

    /**
     * 用户登录
     *
     * @param account  账号
     * @param password 密码
     * @return 单个User实体类
     */
    @Override
    public User queryUserByLogin(String account, String password) {
        User user = null;
        try {
            Connection connection = MysqlTools.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT user.*,role.identity FROM `user`,role WHERE user.type=role.id AND password=? AND (phone=? OR email=?);");
            // 此处可使用phone或email登录，只要有一个与之匹配即登录成功(因为数据库中phone和email都是unique)
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, account);
            preparedStatement.setString(3, account);
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
                Role role = new Role();
                role.setId(resultSet.getInt("type"));
                role.setName(resultSet.getString("identity"));
                user.setRole(role);
            }
            MysqlTools.toFreeResource(connection, preparedStatement, resultSet);
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
    public int insertUserByRegister(User user) {
        int result = 0;
        try {
            Connection connection = MysqlTools.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `user` (name, gender, phone, email, password, image, register, type) VALUES (?, ?, ?, ?, ?, ?, NOW(), ?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getGender());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getImage());
            preparedStatement.setInt(7, user.getRole().getId());

            result = preparedStatement.executeUpdate();
            MysqlTools.toFreeResource(connection, preparedStatement, null);
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
            Connection connection = MysqlTools.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM `user` WHERE id=?");
            preparedStatement.setInt(1, id);

            result = preparedStatement.executeUpdate();
            MysqlTools.toFreeResource(connection, preparedStatement, null);
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
            Connection connection = MysqlTools.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `user` SET name=?,gender=?,phone=?,email=?,password=?,image=?,type=? WHERE id=?");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getGender());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getImage());
            preparedStatement.setInt(7, user.getRole().getId());
            preparedStatement.setInt(8, user.getId());

            result = preparedStatement.executeUpdate();
            MysqlTools.toFreeResource(connection, preparedStatement, null);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("【" + this.getClass() + "这里捕获到了异常】");
            throw new RuntimeException(e);
        }
        return result;
    }
}