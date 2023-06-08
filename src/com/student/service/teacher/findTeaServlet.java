package com.student.service.teacher;

import com.student.dao.teaDao;
import com.student.entity.teaEntity;
import com.student.util.InfoHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author 瑾瑜风禾
 * @Date 2023/6/8 0:07
 */
@WebServlet( name="/findTeaServlet")
public class findTeaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        List<teaEntity> teacherList = null;
        teaDao teaDao = new teaDao();
        teacherList = teaDao.findTeachers();
        response.getWriter().write(InfoHandler.toJSON(teacherList));
    }
}
