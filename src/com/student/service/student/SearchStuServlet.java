package com.student.service.student;

import com.student.dao.studentDao;
import com.student.entity.stuEntity;
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
 * @Date 2023/5/31 18:44
 */
@WebServlet(name="/SearchStuServlet")
public class SearchStuServlet extends HttpServlet {
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

        List<stuEntity> studentList = null;
        studentDao studentDao = new studentDao();
        studentList = studentDao.getSomeStudent(select,element);
        response.getWriter().write(InfoHandler.toJSON(studentList));
    }
}
