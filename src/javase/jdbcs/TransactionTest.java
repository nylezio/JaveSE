package javase.jdbcs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author: codeJerry
 * @description: 事务ACID原则
 * A 原子性：要么全部完成要么都不完成
 * C 一致性：总数不变
 * I 隔离性：多个进程互不干扰
 * D 持久性：一旦提交就持久化到数据库
 *
 * 隔离性：
 * 脏读：一个事务读取了另一个没有提交的事务
 * 不可重复读：在同一个事务内，重复读取表中的数据，表数据发生改变
 * 虚读：在一个事务内，读取到了别人插入的数据，导致前后读出来结果不一致
 * @date: 2020/04/26 20:35
 */
public class TransactionTest {
    public static void main(String[] args) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JdbcUtil.getConnection();

            connection.setAutoCommit(false);

            String sql1 = "update account set money = money - 100  where name = 'A'";
            preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.execute();

//            int x = 1/0;

            String sql2 = "update account set money = money + 100  where name = 'B'";
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.execute();

            connection.commit();
            System.out.println("成功");

        }catch (SQLException e){
            try {
                connection.rollback();
            }catch (Exception e1){
                e.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            JdbcUtil.release(connection,preparedStatement,resultSet);
        }

    }
}
