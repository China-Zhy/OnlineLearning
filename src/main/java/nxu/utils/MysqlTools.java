package nxu.utils;

import java.sql.*;

/**
 * MySQL工具类 (张宏业)
 */
public class MysqlTools {

    // MySQL8.0以上版本的JDBC驱动名
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/onlinelearning";

    // 数据库的用户名和密码
    public static final String USER = "root";
    public static final String PASS = "admin123";

    /**
     * 获得MySQL的Connection对象
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        // 加载JDBC驱动
        Class.forName(MysqlTools.JDBC_DRIVER);

        // 连接MySQL数据库，获得Connection对象
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    /**
     * 关闭MySQL的Connection等对象
     */
    public static void toFreeResource(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) throws SQLException {
        // 为什么这里还有资源的关闭呢，因为如果出错，就会走catch，try后面关闭资源的代码没有被执行；下面的这些判断是为了避免空指针异常；
        if (resultSet != null) resultSet.close();
        if (preparedStatement != null) preparedStatement.close();
        if (connection != null) connection.close();
    }
}