package com.student.dao;

import com.student.entity.adminEntity;
import com.student.util.mysql.connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author 瑾瑜风禾
 * @Date 2023/5/21 21:49
 */
public class adminDao {
    static PreparedStatement pd = null;
    static ResultSet rs;
    static Connection conn = null;

    /**
     * create time: 2023/5/29 15:39
     * 登录
     * @return
     */
    public adminEntity Login(String name, String password){
        adminEntity admin = null;
        ResultSet rs = null;
        String sql = "select * from admin where adminName = ? and password = ?";
        try {
            conn = connect.getConnection();
            pd = conn.prepareStatement(sql);
            pd.setString(1,name);
            pd.setString(2,password);
            rs = pd.executeQuery();
            if(rs.next()){
                admin = new adminEntity();
                admin.setAdminName(rs.getString("adminName"));
                admin.setPassword(rs.getString("password"));
                System.out.println("登录成功！");
            }else {
                System.out.println("登录失败！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connect.close(conn,pd,rs);
        }
        return admin;
    }

    /**
     * create time: 2023/5/22 20:35
     * 添加用户
     * @return
     */
    public void addAdmin(adminEntity admin){
        String name = admin.getName();
        String adminName = admin.getAdminName();
        String password = admin.getPassword();
        String sql = "insert into admin(name,adminName,password) value(?,?,?)";
        try {
            conn = connect.getConnection();
            pd = conn.prepareStatement(sql);
            pd.setString(1,name);
            pd.setString(2,adminName);
            pd.setString(3,password);
            pd.executeUpdate();
            System.out.println("添加一个新用户成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connect.close(conn,pd);
        }
    }

    /**
     * create time: 2023/5/22 21:22
     *用户登录验证
     * @return
     */
    public boolean adminProved(String name, String password){
        String sql = "select * from admin where adminName = ?";
        try {
            conn = connect.getConnection();
            pd = conn.prepareStatement(sql);
            pd.setString(1,name);
            ResultSet rs = pd.executeQuery();
            while (rs.next()){
                String password1 = rs.getString("password");
                return password1.equals(password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connect.close(conn, pd, rs);
        }
        return false;
    }

    /**
     * create time: 2023/5/30 19:38
     * 该用户名是否存在
     * @return
     */
    public boolean isExist(String adminName){
        String sql = "select * from admin where adminName = ?";
        ResultSet rs = null;
        try {
            conn = connect.getConnection();
            pd = conn.prepareStatement(sql);
            pd.setString(1,adminName);
            rs = pd.executeQuery();
            if(rs.next()){
                System.out.println("该用户存在！");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connect.close(conn,pd,rs);
        }
        return true;
    }

    /**
     * create time: 2023/6/2 20:37
     * 修改面码
     * @return
     */
    public boolean isSuccess(String adminName, String newPassword){
        String sql = "update admin set password = ? where adminName = ?";
        try {
            conn = connect.getConnection();
            pd = conn.prepareStatement(sql);
            pd.setString(1,newPassword);
            pd.setString(2,adminName);
            int count = pd.executeUpdate();
            if(count > 0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connect.close(conn, pd);
        }
        return false;
    }
}
