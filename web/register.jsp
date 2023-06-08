<%--
  Created by IntelliJ IDEA.
  User: 瑾瑜风禾
  Date: 2023/5/28
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <link rel="stylesheet" type="text/css" href="css/forms.css">
</head>
<body>
<div class="page">
    <div class="form">
        <div class="title">注<span></span>册</div>
        <form action="/RegisterServlet" method="post" id="formContext">
            <input type="text" minlength="2" maxlength="15" name="username" placeholder="姓名">
            <input type="text" minlength="3" maxlength="20" name="adminName" placeholder="用户名">
            <input type="password" minlength="6" maxlength="20" name="password1" placeholder="密码">
            <input type="password" minlength="6" maxlength="20" name="password2" placeholder="请再次输入密码">
            <button type="submit" class="btn">注册</button>
            <button type="reset" class="btn">重置</button>
        </form>
        <div>
            <p class="message">已有账号？<a href="login.jsp">立即登录</a></p>
        </div>
    </div>
</div>
</body>
</html>
