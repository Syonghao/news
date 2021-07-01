<%--
  Created by IntelliJ IDEA.
  User: wuligang
  Date: 2021/6/16
  Time: 8:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>用户注册</title>
</head>
<body>
<%
    String error = (String)request.getAttribute("error");
%>
<span style="color: red"><%=error==null?"":error%></span>
<form action="register.do" method="post">
    用户名：<input type="text" name="uname" required/><br/>
    密码：<input type="password" name="upwd" required/><br>
    再次输入密码：<input type="password" name="reupwd" required/><br>
    <input type="submit" value="注册"/>
</form>
</body>
</html>
