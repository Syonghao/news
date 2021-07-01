<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2021/6/24
  Time: 10:31
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
您填写的内容是：<br/>
昵称：${user.uname}<br/>
所在城市：${user.cname}<br/>
使用的开发语言有：${user.hobbies[0]}、${user.hobbies[1]}、${user.hobbies[2]}<br/>
<br/>
<br/>
<br/>
<br/>
您填写的内容是：<br/>
昵称：${param.uname}<br/>
所在城市:${param.cname}<br/>
使用的开发语言有：${paramValues.hobby[0]}、${paramValues.hobby[1]}、${paramValues.hobby[2]}
</body>
</html>
