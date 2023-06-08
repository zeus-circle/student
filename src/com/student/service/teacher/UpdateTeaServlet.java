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
import java.io.PrintWriter;

/**
 * @Author 瑾瑜风禾
 * @Date 2023/6/5 20:08
 */
@WebServlet(name = "/UpdateTeaServlet")
public class UpdateTeaServlet extends HttpServlet {
    protected  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String teaOldId = request.getParameter("oldId");
        String teaId = request.getParameter("teaId");
        String teaName = request.getParameter("teaName");
        String teaSex = request.getParameter("teaSex");
        int teaAge = Integer.parseInt(request.getParameter("teaAge"));
        String teaClass = request.getParameter("teaClass");

        teaEntity teaEntity = new teaEntity();
        teaEntity.setTeaId(teaId);
        teaEntity.setTeaName(teaName);
        teaEntity.setTeaSex(teaSex);
        teaEntity.setTeaAge(teaAge);
        teaEntity.setTeaClass(teaClass);

        teaDao teaDao = new teaDao();
        int upRow = teaDao.upTeacher(teaOldId,teaEntity);
        response.getWriter().write(InfoHandler.toJSON(upRow));
    }
}
