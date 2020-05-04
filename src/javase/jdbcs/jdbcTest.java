package javase.jdbcs;

import java.sql.*;

/**
 * @author: codeJerry
 * @description:
 * @date: 2020/04/26 19:17
 */
public class jdbcTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://127.0.0.1:3306/library?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
        String username = "root";
        String password = "abc123456";

        Connection connection = DriverManager.getConnection(url, username, password);

        Statement statement = connection.createStatement();

        String sql = "select * from book";

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()){
            System.out.println("id="+ resultSet.getInt("id"));
            System.out.println("name="+ resultSet.getString("name"));
            System.out.println("author="+ resultSet.getString("author"));
        }

        resultSet.close();
        statement.close();
        connection.close();

    }
}
