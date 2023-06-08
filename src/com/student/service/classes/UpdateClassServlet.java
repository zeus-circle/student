package com.student.service.classes;

import com.student.dao.classDao;
import com.student.entity.classEntity;
import com.student.util.InfoHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author 瑾瑜风禾
 * @Date 2023/6/6 0:21
 */
@WebServlet(name = "/UpdateClassServlet")
public class UpdateClassServlet extends HttpServlet {
    protected  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String classOldId = request.getParameter("oldId");
        String classId = request.getParameter("classId");
        String classesName = request.getParameter("classesName");
        int classNum = Integer.parseInt(request.getParameter("classNum"));
        String teacher = request.getParameter("classTeacher");

        classEntity classEntity = new classEntity();
        classEntity.setClassId(classId);
        classEntity.setClassesName(classesName);
        classEntity.setClassNum(classNum);
        classEntity.setClassTeacher(teacher);

        classDao classDao = new classDao();
        int upRow = classDao.upClass(classOldId,classEntity);
        response.getWriter().write(InfoHandler.toJSON(upRow));
    }
}
