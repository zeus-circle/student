package com.student.service.teacher;

import com.student.dao.studentDao;
import com.student.dao.teaDao;
import com.student.entity.stuEntity;
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
 * @Date 2023/6/5 20:07
 */
@WebServlet(name = "/SearchTeaServlet")
public class SearchTeaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        //获取下拉框的值
        String select = request.getParameter("selectValue");
        String element = request.getParameter("searchElement");

        List<teaEntity> teacherList = null;
        teaDao teaDao = new teaDao();
        teacherList = teaDao.getSomeTeacher(select,element);
        response.getWriter().write(InfoHandler.toJSON(teacherList));
    }
}
