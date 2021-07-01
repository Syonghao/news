<%@ page language="java" import="com.hisoft.news.javabean.News" pageEncoding="utf-8" %>
<%@ page import="com.hisoft.news.javabean.Topic" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>新闻中国</title>
    <link href="css/main.css" rel="stylesheet" type="text/css"/>
    <script src="js/jquery-1.8.3.min.js" type="text/javascript"></script>
</head>

<%--    <c:if test="${empty requestScope.map}">--%>
<%--        <jsp:forward page="/news.do?action=index"></jsp:forward>--%>
<%--    </c:if>--%>
<%--    <c:if test="${not empty requestScope.map}">--%>
<%--       <c:set var="newsList" value="${ map.newsList}" scope="page"/>--%>
<%--       <c:set var="topics" value="${ map.topics}" scope="page"/>--%>

        <body>
        <div id="header">
            <div id="top_login">
<%--                <%--%>
<%--                    String error = (String) request.getAttribute("error");--%>
<%--                %>--%>
                <form action="login.do" method="post">
                    <label> 登录名 </label>
                    <input type="text" id="uname" name="uname" class="login_input"/>
                    <label> 密&#160;&#160;码 </label>
                    <input type="password" id="upwd" name="upwd" class="login_input"/>
                    <input type="submit" value="登录"/> <label id="error" style="color: red">${requestScope.error}
                </label>
                        <%--        <input type="button" class="login_sub" value="登录" onclick="login()"/>--%>
                </form>


                <img src="images/friend_logo.gif" alt="Google" id="friend_logo"/>
            </div>
            <div id="nav">
                <div id="logo"><img src="images/logo.jpg" alt="新闻中国"/></div>
                <div id="a_b01"><img src="images/a_b01.gif" alt=""/></div>
                <!--mainnav end-->
            </div>
        </div>
        <div id="container">
            <%@include file="index-elements/index_left_bar.jsp"%>
            <div class="main">
                <div class="class_type"><img src="images/class_type.gif" alt="新闻中心"/></div>
                <div class="content">
                    <ul class="class_date">
                        <li id='class_month'>
                            <c:forEach items="${applicationScope.topics}" var="topic">
<%--                                <a href='news.do?action=index&tid=${topic.tid}'><b>${topic.tname}</b></a>--%>
                                <a href='javascript:;' onclick="topicNews(${topic.tid})"><b>${topic.tname}</b></a>
                            </c:forEach>


                        </li>
                    </ul>
                    <ul class="classlist">


<%--                        <c:forEach items="${newsList}" var="news" varStatus="v">--%>

<%--                        <li><a href='news.do?action=readnews&nid=${news.nid}'>${news.ntitle}--%>
<%--                        </a><span> ${news.ncreateDate} </span></li>--%>
<%--                        <c:if test="${v.count%5 eq 0}">--%>
<%--                            <li class='space'></li>--%>
<%--                        </c:if>--%>
<%--                        </c:forEach>--%>


                            <%--                <%--%>

                            <%--                    if ((i+1) % 5 == 0) {--%>
                            <%--                %>--%>
                            <%--                <li class='space'></li>--%>
                            <%--                <%--%>
                            <%--                        }--%>
                            <%--                    }--%>
                            <%--                %>--%>


                    </ul>
                    <p align="right"> 当前页数:[1/2]&nbsp; <a href="#">下一页</a> <a href="#">末页</a></p>

                </div>
                <div class="picnews">
                    <ul>
                        <li><a href="#"><img src="images/Picture1.jpg" width="249" alt=""/> </a><a href="#">幻想中穿越时空</a></li>
                        <li><a href="#"><img src="images/Picture2.jpg" width="249" alt=""/> </a><a href="#">国庆多变的发型</a></li>
                        <li><a href="#"><img src="images/Picture3.jpg" width="249" alt=""/> </a><a href="#">新技术照亮都市</a></li>
                        <li><a href="#"><img src="images/Picture4.jpg" width="249" alt=""/> </a><a href="#">群星闪耀红地毯</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div id="footer">
            <iframe src="index-elements/index_bottom.html" scrolling="no" frameborder="0" width="947px" height="190px"></iframe>
        </div>

        <script type="text/javascript">
            function topicNews(tid) {
                var $ul = $("ul[class = classlist]");
                $ul.html("");
                var param;
                if(tid==0){
                    param = "action=index"
                }else{
                    param = "action=index&tid="+tid;
                }
                $.ajax({
                    url:"news",
                    type:"get",
                    data:param,
                    dataType:"json",
                    success:function (result) {

                        for(var i = 0;i < result.length;i++){
                            var $li = $("<li><a href='news?action=readnews&nid="+result[i].nid+"'>"+result[i].ntitle+"</a><span>"+result[i].ncreateDate+"</span></li>")
                            $ul.append($li);
                            if((i+1)%5==0){
                                $ul.append("<li class='space'></li>")
                            }
                        }
                    }
                })
            }
            $(function () {
                topicNews(0);//首次进入首页
            })
        </script>
        </body>

<%--    </c:if>--%>



</html>
	