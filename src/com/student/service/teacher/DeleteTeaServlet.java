package com.student.service.teacher;

import com.student.dao.studentDao;
import com.student.dao.teaDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author 瑾瑜风禾
 * @Date 2023/6/5 20:07
 */
@WebServlet(name="/DeleteTeaServlet")
public class DeleteTeaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String teaId = request.getParameter("teaId");
        teaDao teaDao = new teaDao();
        boolean flag = teaDao.delTeacher(teaId);
        response.getWriter().write(String.valueOf(flag));
    }
}
