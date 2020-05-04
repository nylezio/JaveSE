package javase.jdbcs;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author: codeJerry
 * @description:
 * @date: 2020/04/26 20:09
 */
public class JdbcUtil {

    private static String driver = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;

    static {
        try {
            InputStream resourceAsStream = jdbcTest.class.getClassLoader().getResourceAsStream("db.properties");
            Properties properties = new Properties();
            properties.load(resourceAsStream);

            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");

            Class.forName(driver);

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public static void release(Connection connection, Statement statement, ResultSet resultSet){
        if (resultSet != null){
            try {
                resultSet.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
        }
        if (statement != null){
            try {
                statement.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        if (connection != null){
            try {
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }


    }

}
