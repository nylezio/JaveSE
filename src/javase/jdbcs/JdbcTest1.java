package javase.jdbcs;

import java.sql.*;

/**
 * @author: codeJerry
 * @description:
 * @date: 2020/04/26 20:20
 */
public class JdbcTest1 {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtil.getConnection();
            //此方法有sql注入的危险，使用PreparedStatement可以防止
            statement = connection.createStatement();

            String sql = "select * from book";
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                System.out.println("id="+ resultSet.getInt("id"));
                System.out.println("name="+ resultSet.getString("name"));
                System.out.println("author="+ resultSet.getString("author"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtil.release(connection,statement,resultSet);
        }

    }
}
