<%--
  Created by IntelliJ IDEA.
  User: wuligang
  Date: 2021/6/15
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
<%--    所有超链接都从根目录下查找--%>
    <base href="<%=basePath%>">
    <title>Title</title>
</head>
<body>
<%
//    String user = (String)session.getAttribute("user");
//    if (user == null) {
//        response.sendRedirect("../index.jsp");
//        return;
//    }
%>
</body>
</html>
