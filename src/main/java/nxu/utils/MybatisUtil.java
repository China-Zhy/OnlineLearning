package nxu.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * MyBatis的工具类 (张宏业)
 */
public class MybatisUtil {

    private static final SqlSessionFactory factory;
    private static final String resource = "mybatis-config.xml";
    private static final InputStream inputStream;

    static {
        try {
            inputStream = Resources.getResourceAsStream(resource);
            factory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            System.out.println("【MyBatis工具类出现异常】");
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取SqlSession
     */
    public static SqlSession getSqlSession() {
        return factory.openSession(true);   // 此处开启默认提交
    }
}