package com.student.service.classes;

import com.student.dao.classDao;
import com.student.dao.studentDao;
import com.student.entity.classEntity;
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
 * @Date 2023/6/6 0:20
 */
@WebServlet(name = "/AddClassServlet")
public class AddClassServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String classId = request.getParameter("classId");
        String className = request.getParameter("classesName");
        int num = Integer.parseInt(request.getParameter("classNum"));
        String teacher = request.getParameter("classTeacher");
        classEntity classEntity = new classEntity();
        classEntity.setClassId(classId);
        classEntity.setClassesName(className);
        classEntity.setClassNum(num);
        classEntity.setClassTeacher(teacher);

        classDao classDao = new classDao();
        classDao.addClass(classEntity);
        System.out.println("添加课程信息成功！");
    }
}
