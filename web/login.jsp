<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2021/6/22
  Time: 10:05
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
    <title>Title</title>
</head>
<body>
<form action="login" method="post">
    <label> 登录名 </label>
    <input type="text" id="uname" name="uname" class="login_input"/>
    <label> 密&#160;&#160;码 </label>
    <input type="password" id="upwd" name="upwd" class="login_input"/>
    <input type="submit" value="登录"/> <label id="error" style="color: red">
</label>
</form>
</body>
</html>
