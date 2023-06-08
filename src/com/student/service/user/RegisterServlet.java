package com.student.service.user;

import com.student.dao.adminDao;
import com.student.entity.adminEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author 瑾瑜风禾
 * @Date 2023/5/29 20:19
 */
@WebServlet(name="/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("username");
        String nick = request.getParameter("adminName");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");

        if("".equals(name) || "".equals(name.trim())){
            System.out.println("姓名不能为空");
            return;
        }

        if("".equals(nick) || "".equals(nick.trim())){
//            out.println("<font color=red>用户名不能为空</font>");
            System.out.println("用户名不能为空");
            return;
        }else {
            adminDao adminDao = new adminDao();
            boolean flag = adminDao.isExist(nick);
            if(!flag){
//                out.println("<font color=red>该用户名已存在</font>");
                System.out.println("该用户名已存在");
                return;
            }
        }

        if(password2.equals(password1)){
            Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{6,20}$");
            Matcher matcher = pattern.matcher(password1);
            if(!matcher.matches()){
//                out.println("<font color=red>密码长度要求在6-20位之间，只能包含字母和数字，不能包含特殊字符！");
                System.out.println("密码输入格式不正确");
                return;
            }
        }else{
//            out.println("<font color=red>两次密码不一致</font>");
            System.out.println("第二次密码与第一次密码不同");
            return;
        }

        adminEntity adminEntity = new adminEntity();
        adminEntity.setName(name);
        adminEntity.setAdminName(nick);
        adminEntity.setPassword(password1);

        adminDao adminDao = new adminDao();
        adminDao.addAdmin(adminEntity);
        System.out.println("注册新用户成功！");
        request.getRequestDispatcher("login.jsp").forward(request,response);
    }
}
