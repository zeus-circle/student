<%--
  Created by IntelliJ IDEA.
  User: 瑾瑜风禾
  Date: 2023/5/29
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主界面</title>
    <link rel="stylesheet" type="text/css" href="css/homePage.css">
</head>
<body>
<div>
    <div class="headline">
        <div class="iconfont navigation" id="selectText">&#xe63b;</div>
        <div class="iconfont" id="loginOut"><span>注销</span><a href="/loginOut.jsp">&#xe673;</a></div>
    </div>
    <div class="title">
        <h1>欢迎来到信息管理系统</h1>
    </div>
    <div id="sidebar" class="sidebar">
        <div class="sidebarTitle">
            <div>导航</div>
            <div id="cancel">
                <svg width="16" height="16" viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" clip-rule="evenodd" d="M15.8838 14.7797L9.10412 8L15.8838 1.22034C16.0387 1.06538 16.0387 0.823245 15.8838 0.668281L15.3317 0.116223C15.1768 -0.0387409 14.9346 -0.0387409 14.7797 0.116223L8 6.89588L1.22034 0.116223C1.06538 -0.0387409 0.823245 -0.0387409 0.668281 0.116223L0.116223 0.668281C-0.0387409 0.823245 -0.0387409 1.06538 0.116223 1.22034L6.89588 8L0.116223 14.7797C-0.0387409 14.9346 -0.0387409 15.1768 0.116223 15.3317L0.668281 15.8838C0.823245 16.0387 1.06538 16.0387 1.22034 15.8838L8 9.10412L14.7797 15.8838C14.9346 16.0387 15.1768 16.0387 15.3317 15.8838L15.8838 15.3317C16.0387 15.1768 16.0387 14.9346 15.8838 14.7797Z" fill="white"/>
                </svg>
            </div>
        </div>
        <ul>
            <li><a href="html/classes.html">课程信息</a></li>
            <li><a href="html/teacher.html">教师信息</a></li>
            <li><a href="html/student.html">学生信息</a></li>
        </ul>
    </div>
</div>
<script>
    let sidebar = document.getElementById('sidebar');
    let falseName;
    let selectText=document.getElementById('selectText')
    selectText.addEventListener("click",function (){
        sidebar.removeEventListener("animationend",endOver,true);
        selectText.style.visibility = "hidden";
        sidebar.style.display = "block";
        sidebar.style.animationName="right";
        falseName="selectText"
    })

    let cancel=document.getElementById('cancel');
    cancel.addEventListener("click", function (){
        selectText.style.visibility = "visible";
        sidebar.style.animation = "left 1s ease";
        console.log(2)
        sidebar.addEventListener("animationend",endOver,true);
        console.log(4)

    })

    function endOver(){
        sidebar.style.display="none";
        console.log(3)
    }
</script>
</body>
</html>
