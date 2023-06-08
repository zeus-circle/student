package com.student.util.mysql;

import java.sql.*;

/**
 * @Author 瑾瑜风禾
 * @Date 2023/5/21 21:14
 */
public class connect {
    private static final String URL = "jdbc:mysql://localhost:3306/stumanage";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";
    private static Connection conn = null;
    static {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * create time: 2023/5/21 21:25
     * 获取数据库连接
     * @return
     */
    public static Connection getConnection() throws SQLException{
        return conn = DriverManager.getConnection(URL,USER,PASSWORD);
    }

    /**
     * create time: 2023/5/21 21:25
     * SQL命令执行器
     * @return
     */
    public static PreparedStatement preparedStatement(String sql) throws Exception{
        return getConnection().prepareStatement(sql);
    }

    /**
     * create time: 2023/5/22 11:24
     *
     * @return
     */
    public static void close(Connection connection, PreparedStatement preparedStatement){
        if(preparedStatement !=null){
            try{
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(connection !=null){
            try{
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
        if(resultSet !=null){
            try{
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(preparedStatement !=null){
            try{
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(connection !=null){
            try{
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
