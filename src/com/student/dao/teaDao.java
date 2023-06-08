package com.student.dao;

import com.student.entity.teaEntity;
import com.student.util.mysql.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 瑾瑜风禾
 * @Date 2023/5/23 20:14
 */
public class teaDao {
    static PreparedStatement pd = null;
    static ResultSet rs;
    static Connection conn = null;

    /**
     * create time: 2023/5/23 20:16
     * 增加老师信息
     * @return
     */
    public void addTeacher(teaEntity teacher){
        String sql = "insert into teacher(teaId, teaName, teaSex, teaAge, teaClass) value(?,?,?,?,?)";
        try {
            conn = connect.getConnection();
            pd = conn.prepareStatement(sql);
            pd.setString(1, teacher.getTeaId());
            pd.setString(2, teacher.getTeaName());
            pd.setString(3, teacher.getTeaSex());
            pd.setInt(4, teacher.getTeaAge());
            pd.setString(5, teacher.getTeaClass());
            pd.executeUpdate();
            System.out.println("添加教师信息成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connect.close(conn, pd);
        }
    }

    /**
     * create time: 2023/5/23 21:18
     * 删除老师信息
     * @return
     */
    public boolean delTeacher(String teacherId){
        boolean flag = false;
        String sql = "delete from teacher where teaId = ?";
        try {
            conn = connect.getConnection();
            pd = conn.prepareStatement(sql);
            pd.setString(1,teacherId);
            flag = pd.executeUpdate() > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connect.close(conn, pd);
        }
        return flag;
    }

    /**
     * create time: 2023/5/23 21:23
     * 更新老师信息
     * @return
     */
    public int upTeacher(String teaOldId,teaEntity teacher){
        String sql = "update teacher set teaId = ?, teaName = ?, teaSex = ?, teaAge = ?, teaClass = ? where teaId = ?";
        int updateRow = 0;
        try {
            conn = connect.getConnection();
            pd = conn.prepareStatement(sql);
            pd.setString(1,teacher.getTeaId());
            pd.setString(2, teacher.getTeaName());
            pd.setString(3, teacher.getTeaSex());
            pd.setInt(4, teacher.getTeaAge());
            pd.setString(5, teacher.getTeaClass());
            pd.setString(6, teaOldId);
            System.out.println("更新教师信息成功！");
            updateRow = pd.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connect.close(conn, pd);
        }
        return updateRow;
    }
    /**
     * create time: 2023/5/23 21:26
     * 查询满足条件的老师信息
     * @return
     */
    public List<teaEntity> getSomeTeacher(String select, String element){
        List<teaEntity> list = new ArrayList<>();
        //1=1用于占位，方便后续需拼接条件
        StringBuilder sql = new StringBuilder();
        sql.append("select * from teacher where " + select + " like ?");
        try {
            conn = connect.getConnection();
            pd = conn.prepareStatement(sql.toString());
            pd.setString(1,'%'+element + '%');
            ResultSet rs = pd.executeQuery();
            while (rs.next()){
                teaEntity teacher = new teaEntity();
                teacher.setTeaId(rs.getString("teaId"));
                teacher.setTeaName(rs.getString("teaName"));
                teacher.setTeaSex(rs.getString("teaSex"));
                teacher.setTeaAge(rs.getInt("teaAge"));
                teacher.setTeaClass(rs.getString("teaClass"));
                list.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connect.close(conn, pd);
        }
        return list;
    }

    /**
     * create time: 2023/6/7 23:44
     * 获取所有老师的信息
     * @return
     */
    public List<teaEntity> findTeachers(){
        String sql = "select * from teacher";
        //定义一列表，存储数据
        List<teaEntity> teachers = new ArrayList<>();
        try {
            conn = connect.getConnection();
            pd = conn.prepareStatement(sql);
            ResultSet rs = pd.executeQuery();
            while (rs.next()){
                String teacherId = rs.getString(1);
                String teacherName = rs.getString(2);
                String teacherSex = rs.getString(3);
                Integer teacherAge = rs.getInt(4);
                String teacherClass = rs.getString(5);
                //封装实体型
                teaEntity tea = new teaEntity();
                tea.setTeaId(teacherId);
                tea.setTeaName(teacherName);
                tea.setTeaSex(teacherSex);
                tea.setTeaAge(teacherAge);
                tea.setTeaClass(teacherClass);
                teachers.add(tea);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            connect.close(conn,pd,rs);
        }
        return teachers;
    }
}
