package com.student.service.student;

import com.student.dao.studentDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * @Author 瑾瑜风禾
 * @Date 2023/5/31 2:48
 */
@WebServlet(name="/DeleteStuServlet")
public class DeleteStuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String stuId = request.getParameter("stuId");
        studentDao studentDao = new studentDao();
        boolean flag = studentDao.delStudent(stuId);
        response.getWriter().write(String.valueOf(flag));
    }
}
