package com.student.listener;

/**
 * @Author 瑾瑜风禾
 * @Date 2023/5/29 15:06
 */

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebServlet;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

/**
 * create time: 2023/5/29 15:06
 * 用于解决mysql内存溢出
 * @return
 */
public class MyServletListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //这里如果Web应用拥有多个数据库的连接，可以一并关闭
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        Driver driver = null;
        while (drivers.hasMoreElements()) {
            try {
                driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);
            } catch (SQLException ex) {
            }
        }

    }
}
