package com.student.dao;

import com.student.entity.classEntity;
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
 * @Date 2023/5/23 11:08
 */
public class classDao {
    static PreparedStatement pd = null;
    static ResultSet rs = null;
    static Connection conn = null;

    /**
     * create time: 2023/5/23 11:11
     * 增加班级信息
     * @return
     */
    public void addClass(classEntity classEntity){
        String sql = "insert into classes(classId, classesName, classNum, classTeacher) values(?,?,?,?)";
        try {
            conn = connect.getConnection();
            pd = conn.prepareStatement(sql);
            pd.setString(1,classEntity.getClassId());
            pd.setString(2,classEntity.getClassesName());
            pd.setInt(3,classEntity.getClassNum());
            pd.setString(4,classEntity.getClassTeacher());
            pd.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connect.close(conn,pd);
        }
    }

    /**
     * create time: 2023/5/23 11:32
     * 学生或老师的信息中的班级是否存在
     * @return
     */
    public classEntity isHaveClass(String classesName){
        classEntity classEntity = null;
        String sql = "select * from classes where classesName = ?";
        try{
            conn = connect.getConnection();
            pd = conn.prepareStatement(sql);
            pd.setString(1,classesName);
            ResultSet rs = pd.executeQuery();
            if(rs.next()){
                classEntity = new classEntity();
                classEntity.setClassId(rs.getString("classId"));
                classEntity.setClassesName(rs.getString("classesName"));
                classEntity.setClassNum(rs.getInt("classNum"));
                classEntity.setClassTeacher(rs.getString("classTeacher"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connect.close(conn, pd, rs);
        }
        return classEntity;
    }

    /**
     * create time: 2023/5/23 14:58
     * 更新学生数量(学生数量这个问题还未考虑周全）
     * @return
     */
    public int upSum(classEntity classEntity, int i){
        int sum = (classEntity.getClassNum() + i);
        if(sum < 0){
            return -1;
        }
        String sql = "update classes set classNum = '" + sum + "' where classesName = '"+classEntity.getClassesName() + "'";
        try {
            conn = connect.getConnection();
            pd = conn.prepareStatement(sql);
            pd.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connect.close(conn, pd);
        }
        return 0;
    }

    /**
     * create time: 2023/5/23 15:44
     * 查询所有课程信息
     * @return
     */
    public List<classEntity> getClassAll(){
        List<classEntity> list = new ArrayList<>();
        String sql = "select * from classes";
        try {
            conn = connect.getConnection();
            pd = conn.prepareStatement(sql);
            ResultSet rs = pd.executeQuery();
            while (rs.next()){
                classEntity classEntity = null;
                classEntity.setClassId(rs.getString("classId"));
                classEntity.setClassesName(rs.getString("classesName"));
                classEntity.setClassNum(rs.getInt("classNum"));
                classEntity.setClassTeacher(rs.getString("classTeacher"));
                list.add(classEntity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connect.close(conn, pd, rs);
        }
        return list;
    }

    /**
     * create time: 2023/5/23 19:58
     * 根据课程名或老师名字进行查询
     * @return
     */
    public List<classEntity> getSomeClass(String select, String element){
        List<classEntity> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append( "SELECT * FROM classes WHERE " + select + " like ?");
        try{
            conn = connect.getConnection();
            pd = conn.prepareStatement(sql.toString());
            pd.setString(1,'%'+element + '%');
            ResultSet rs = pd.executeQuery();
            while (rs.next()){
                classEntity classEntity = new classEntity();
                classEntity.setClassId(rs.getString("classId"));
                classEntity.setClassesName(rs.getString("classesName"));
                classEntity.setClassNum(rs.getInt("classNum"));
                classEntity.setClassTeacher(rs.getString("classTeacher"));
                list.add(classEntity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connect.close(conn, pd ,rs);
        }
        return list;
    }

    /**
     * create time: 2023/5/23 15:59
     * 删除课程信息
     * @return
     */
    public boolean delClass(String classId){
        boolean flag = false;
        String sql = "delete from classes where classId = ?";
        try{
            conn = connect.getConnection();
            pd = conn.prepareStatement(sql);
            pd.setString(1,classId);
            flag = pd.executeUpdate() > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connect.close(conn, pd);
        }
        return flag;
    }

    /**
     * create time: 2023/5/23 19:34
     * 更新课程信息
     * @return
     */
    public int upClass(String classOldId,classEntity classEntity){
        String sql = "update classes set classId = ?, classesName = ?, classNum = ?, classTeacher = ? where classId = ?";
        int updateRow = 0;
        try {
            conn = connect.getConnection();
            pd = conn.prepareStatement(sql);
            pd.setString(1,classEntity.getClassId());
            pd.setString(2,classEntity.getClassesName());
            pd.setInt(3,classEntity.getClassNum());
            pd.setString(4,classEntity.getClassTeacher());
            pd.setString(5,classOldId);
            System.out.println("更新班级信息成功！");
            updateRow = pd.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connect.close(conn, pd);
        }
        return updateRow;
    }

    /**
     * create time: 2023/6/7 23:44
     * 获取所有课程的信息
     * @return
     */
    public List<classEntity> findClasses(){
        String sql = "select * from classes";
        //定义一列表，存储数据
        List<classEntity> classes = new ArrayList<>();
        try {
            conn = connect.getConnection();
            pd = conn.prepareStatement(sql);
            ResultSet rs = pd.executeQuery();
            while (rs.next()){
                String classId = rs.getString(1);
                String className = rs.getString(2);
                Integer classNum = rs.getInt(3);
                String classTeacher = rs.getString(4);
                //封装实体型
                classEntity classEntity = new classEntity();
                classEntity.setClassId(classId);
                classEntity.setClassesName(className);
                classEntity.setClassNum(classNum);
                classEntity.setClassTeacher(classTeacher);
                classes.add(classEntity);
                System.out.println(classTeacher);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            connect.close(conn,pd,rs);
        }
        return classes;
    }
}
