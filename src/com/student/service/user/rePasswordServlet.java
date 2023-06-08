package com.student.service.user;

import com.student.dao.adminDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.student.util.Constants;
import com.student.util.InfoHandler;


/**
 * @Author 瑾瑜风禾
 * @Date 2023/6/2 15:28
 */
@WebServlet(name="/rePasswordServlet")
public class rePasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        //从session中获取数据
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        String newPassword = request.getParameter("newPassword");
        String oldPassword = request.getParameter("oldPassword");
        String truePassword = request.getParameter("truePassword");
        PrintWriter out = response.getWriter();
        if(newPassword.equals(truePassword)) {
            Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{6,20}$");
            Matcher matcher = pattern.matcher(newPassword);
            if (!matcher.matches()) {
                out.println("<font color=red>密码长度要求在6-20位之间，只能包含字母和数字，不能包含特殊字符！");
                System.out.println("密码输入格式不正确");
                return;
            }
        }else {
            System.out.println("两次密码输入不一致");
        }

        boolean flag = false;
        if (password!=null && password.equals(oldPassword)){
            adminDao adminDao = new adminDao();
            flag = adminDao.isSuccess(username,newPassword);
            if(flag){
                response.getWriter().write(InfoHandler.toJSON(flag));
                request.getSession().removeAttribute(Constants.USER_SESSION);
                response.sendRedirect("login.jsp");
            }else {
                System.out.println("修改失败");
            }
        }else
            System.out.println("原始密码输入不正确！");
    }
}
