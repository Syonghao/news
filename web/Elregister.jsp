<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2021/6/24
  Time: 10:18
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

<form action="hello.do" method="post">
    昵称：<input type="text" name="uname" required/><br/>
    所在城市：<input type="text" name="cname" required/><br>
    您所使用的开发语言：
    <input type="checkbox" name="hobby" value="java" />java<br>
    <input type="checkbox" name="hobby" value="php" />php<br>
    <input type="checkbox" name="hobby" value="c++" />c++<br>
    <input type="checkbox" name="hobby" value="web" />web<br>
    <input type="checkbox" name="hobby" value="play" />play<br>
    <input type="submit" value="注册"/>
</form>
</body>
</html>
