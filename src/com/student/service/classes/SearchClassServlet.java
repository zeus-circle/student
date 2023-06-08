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
import java.util.List;

/**
 * @Author 瑾瑜风禾
 * @Date 2023/6/6 0:22
 */
@WebServlet(name = "/SearchClassServlet")
public class SearchClassServlet extends HttpServlet {
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

        List<classEntity> classList = null;
        classDao classDao = new classDao();
        classList = classDao.getSomeClass(select,element);
        response.getWriter().write(InfoHandler.toJSON(classList));
    }
}
