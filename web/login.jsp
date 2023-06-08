<%--
  Created by IntelliJ IDEA.
  User: 瑾瑜风禾
  Date: 2023/5/23
  Time: 21:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录界面</title>
    <link rel="stylesheet" type="text/css" href="css/forms.css">
</head>
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<body>
<div class="page">
    <div class="form">
        <div class="title">登<span> </span>录</div>
        <form action="/LoginServlet" method="post" id="formContext">
                <input type = "text" placeholder = "用户名" name = "uname"/><br>
                <input type = "password" placeholder = "密码" name = "upwd"/><br>
                <span id="msg" style="font-size: 12px;color: red"></span><br>
                <button class="btn" type="submit">登陆</button>
                <button class="btn" type="reset">重置</button>
            <div>
                <p class="message">还没有账号？<a href="register.jsp">立即注册</a></p>
            </div>
        </form>
    </div>
</div>
</body>
</html>
