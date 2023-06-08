package com.student.dao;

import com.student.entity.stuEntity;
import com.student.util.mysql.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author 瑾瑜风禾
 * @Date 2023/5/21 20:44
 */
public class studentDao {
    static PreparedStatement pd = null;
    static ResultSet rs;
    static Connection conn = null;

    /**
     * create time: 2023/5/21 21:28
     * 增加学生
     * @return
     */
    public void addStudent(stuEntity student){
        String sql = "insert into student(stuId, stuName, stuSex, stuAge, stuGrade, stuClass) value(?,?,?,?,?,?)";
        try{
            conn = connect.getConnection();
            pd = conn.prepareStatement(sql);
            pd.setString(1,student.getStuId());
            pd.setString(2,student.getStuName());
            pd.setString(3,student.getStuSex());
            pd.setInt(4,student.getStuAge());
            pd.setInt(5,student.getStuGrade());
            pd.setString(6,student.getStuClass());
            pd.executeUpdate();
            System.out.println("添加学生信息成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connect.close(conn, pd);
        }
    }
//
//    /**
//     * create time: 2023/5/31 16:23
//     * 该学号是否存在
//     * @return
//     */
//    public boolean isExist(String stuId){
//        String sql = "select * from student where stuId = ?";
//        ResultSet rs = null;
//        try {
//            conn = connect.getConnection();
//            pd = conn.prepareStatement(sql);
//            pd.setString(1,stuId);
//            rs = pd.executeQuery();
//            if(rs.next()){
//                System.out.println("该学号已经存在！");
//                return false;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            connect.close(conn,pd,rs);
//        }
//        return true;
//    }

    /**
     * create time: 2023/5/22 11:30
     * 删除学生
     * @return
     */
    public boolean delStudent(String studentId){
        boolean flag = false;
        String sql = "delete from student where stuId = ?";
        try {
            conn = connect.getConnection();
            pd = conn.prepareStatement(sql);
            pd.setString(1,studentId);
            flag = pd.executeUpdate() > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connect.close(conn, pd);
        }
        return flag;
    }

    /**
     * create time: 2023/5/22 14:35
     * 更新学生信息
     * @return
     */
    public int upStudent(String stuOldId,stuEntity student){
        String sql = "update student set stuId =?,stuName = ?, stuSex = ?, stuAge = ?, stuGrade = ?, stuClass = ? where stuId = ?";
        int updateRow = 0;
        try{
            conn = connect.getConnection();
            pd = conn.prepareStatement(sql);
            pd.setString(1,student.getStuId());
            pd.setString(2, student.getStuName());
            pd.setString(3,student.getStuSex());
            pd.setInt(4,student.getStuAge());
            pd.setInt(5,student.getStuGrade());
            pd.setString(6,student.getStuClass());
            pd.setString(7,stuOldId);
            System.out.println("更新学生信息成功！");
            updateRow = pd.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connect.close(conn , pd);
        }
        return updateRow;
    }

    /**
     * create time: 2023/5/22 14:53
     * 查询所有学生的信息
     * @return
     */
    public List<stuEntity> getAllStudent(){
        List<stuEntity> list = new ArrayList<>();
        String sql = "select * from student";
        try {
            conn = connect.getConnection();
            pd = conn.prepareStatement(sql);
            ResultSet rs = pd.executeQuery();
            while (rs.next()){
                stuEntity stu = new stuEntity();
                stu.setStuId(rs.getString("stuId"));
                stu.setStuName(rs.getString("stuName"));
                stu.setStuSex(rs.getString("stuSex"));
                stu.setStuAge(rs.getInt("stuAge"));
                stu.setStuGrade(rs.getInt("stuGrade"));
                stu.setStuClass(rs.getString("stuClass"));
                list.add(stu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connect.close(conn , pd ,rs);
        }
        return list;
    }

    /**
     * create time: 2023/5/22 19:03
     *查询
     * @return
     */
    public List<stuEntity> getSomeStudent(String select, String element){
        List<stuEntity> students = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append( "SELECT * FROM student WHERE " + select + " like ?");
        try{
            conn = connect.getConnection();
            pd = conn.prepareStatement(sql.toString());
            pd.setString(1,'%'+element + '%');
            ResultSet rs = pd.executeQuery();
            while (rs.next()){
                stuEntity stu = new stuEntity();
                stu.setStuId(rs.getString("stuId"));
                stu.setStuName(rs.getString("stuName"));
                stu.setStuSex(rs.getString("stuSex"));
                stu.setStuAge(rs.getInt("stuAge"));
                stu.setStuGrade(rs.getInt("stuGrade"));
                stu.setStuClass(rs.getString("stuClass"));
                students.add(stu);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connect.close(conn,pd,rs);
        }
        return students;
    }

}
