<%--
  Created by IntelliJ IDEA.
  User: 瑾瑜风禾
  Date: 2023/5/29
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户修改密码界面</title>
    <link rel="stylesheet" type="text/css" href="css/forms.css">
</head>
<body>
<div class="page">
    <div class="form">
        <div class="title">修改密码</div>
        <form id="formContext" name="userForm" action="/rePasswordServlet" method="post">
                <input type="password" id="oPwd" class="inp" name="oldPassword" placeholder="旧密码">
                <input type="password" id="rPwd" class="inp" name="newPassword" placeholder="新密码">
                <input type="password" id="tPwd" class="inp" name="truePassword" placeholder="请确认密码">
                <button id="save" name="save" class="btn">保存</button>
            <div>
                <p class="message">不想修改密码？<a href="#" id="back">返回</a></p>
            </div>
        </form>
    </div>
</div>
<script>
    //返回上一页
    document.getElementById("back").addEventListener("click",back);
    function back(){
        window.history.go(-1);
    }
</script>
</body>
</html>
