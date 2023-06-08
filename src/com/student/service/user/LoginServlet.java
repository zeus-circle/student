package com.student.service.user;

import com.student.dao.adminDao;
import com.student.entity.adminEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author 瑾瑜风禾
 * @Date 2023/5/29 15:19
 */
@WebServlet(name = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("uname");
        String password = request.getParameter("upwd");
        adminDao adminDao = new adminDao();
        HttpSession session = request.getSession();
        boolean flag = adminDao.isExist(name);
        if(flag){
            System.out.println("该用户不存在");
            response.sendRedirect("register.jsp");
        }else {
            adminEntity admin = adminDao.Login(name,password);
            if(admin != null){
                session.setAttribute("username",name);
                session.setAttribute("password",password);
                //请求转发跳转
                request.getRequestDispatcher("homePage.jsp").forward(request,response);
            }else {
                System.out.println("密码错误");
            }
        }
    }
}
