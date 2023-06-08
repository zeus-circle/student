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
 * @Date 2023/6/8 2:40
 */
//你要是用的xml映射就要加上name=，有两种方法，一种就是用xml文件，就是你目录下面的web.xml，你一个累就要写一个下面的这个注解，要用name=来标识，还有一种方法就是
//直接用这个注解，不需要写web.xml文件，这个方法就不用写name=，可以直接写后面的字符串，就直接对应这个类
@WebServlet(name = "/findClassServlet")
public class findClassServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        List<classEntity> classList = null;
        classDao classDao = new classDao();
        classList = classDao.findClasses();
        response.getWriter().write(InfoHandler.toJSON(classList));
    }
}