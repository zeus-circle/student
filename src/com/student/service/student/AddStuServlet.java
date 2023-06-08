package com.student.service.student;

import com.student.dao.studentDao;
import com.student.entity.stuEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * @Author 瑾瑜风禾
 * @Date 2023/5/31 1:49
 */
@WebServlet(name = "/AddStuServlet")
public class AddStuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String stuId = request.getParameter("stuId");
        String stuName = request.getParameter("stuName");
        String stuSex = request.getParameter("stuSex");
        int stuAge = Integer.parseInt(request.getParameter("stuAge"));
        int stuGrade = Integer.parseInt(request.getParameter("stuGrade"));
        String stuClass = request.getParameter("stuClass");
        stuEntity stuEntity = new stuEntity();
        stuEntity.setStuId(stuId);
        stuEntity.setStuName(stuName);
        stuEntity.setStuSex(stuSex);
        stuEntity.setStuAge(stuAge);
        stuEntity.setStuGrade(stuGrade);
        stuEntity.setStuClass(stuClass);

        studentDao  studentDao = new studentDao();
        studentDao.addStudent(stuEntity);
        System.out.println("添加学生信息成功！");
    }
}
