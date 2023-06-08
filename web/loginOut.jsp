<%--
  Created by IntelliJ IDEA.
  User: 瑾瑜风禾
  Date: 2023/6/7
  Time: 0:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注销页</title>
</head>
<body>
<%
    session.invalidate();
    response.sendRedirect("index.jsp");
%>
</body>
</html>