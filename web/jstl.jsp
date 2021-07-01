<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2021/6/24
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<%--<%--%>
<%--    NewsUser user = new NewsUser();--%>
<%--    user.setUname("李四");--%>
<%--    request.setAttribute("user",user);--%>
<%--%>--%>
<%--&lt;%&ndash;set标签&ndash;%&gt;--%>
<%--    <c:set var="name" value="张三" scope="request"/>--%>

<%--&lt;%&ndash;相当于request.setAttribute("name","张三")&ndash;%&gt;--%>

<%--    <c:set target="${user}" property="uname" value="王五"/>--%>
<%--name:${user.uname}--%>
<%
    Map map = new HashMap<>();
    map.put("name","张三");
    map.put("pwd","123");
    map.put("gender","男");
    map.put("age","23");
    map.put("birthday","7.23");
    map.put("hello","123");
    request.setAttribute("map",map);
%>
<c:forEach items="${map}" var="m">
    ${m.key}:${m.value}
</c:forEach>
</body>
</html>
