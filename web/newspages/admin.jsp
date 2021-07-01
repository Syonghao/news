<%@ page language="java" import="com.hisoft.news.javabean.News" pageEncoding="utf-8" %>
<%@ page import="java.util.List" %>
<%@ include file="controll_util.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>添加主题--管理后台</title>
    <link href="css/admin.css" rel="stylesheet" type="text/css"/>
</head>


        <c:if test="${empty newsPage}">
            <jsp:forward page="/news?action=backnews"></jsp:forward>
        </c:if>
<c:if test="${not empty newsPage}">
    <body>
    <div id="main">
        <div>
                <%--	    <iframe src="../newspages/console_element/top.html" scrolling="no" frameborder="0" width="947px" height="180px"></iframe>--%>
            <%@ include file="console_element/top.html" %>
        </div>
        <div id="opt_list">
                <%--	 	<iframe src="../newspages/console_element/left.html" scrolling="no" frameborder="0" width="130px"></iframe>--%>
            <%@ include file="console_element/left.html" %>
        </div>
        <div id="opt_area">
            <ul class="classlist">

                <c:forEach items="${newsPage.newsList}" var="news" varStatus="v">
                    <li>${news.ntitle}
                        <span> 作者：${news.nauthor}   &#160;&#160;&#160;&#160;
					<a href='news_modify.jsp'>修改</a> &#160;&#160;&#160;&#160;
					<a href='javascript:;' onclick="del(${news.nid})">删除</a>
				</span>
                    </li>
                    <c:if test="${v.count%5 eq 0}">
                        <li class='space'></li>
                    </c:if>
                </c:forEach>

                <p align="right">
                    当前页数:[${newsPage.currPageNo}/${newsPage.totalPageCount}]&nbsp;
                    <a href="news.do?action=backnews&currentPageNo=1">首页</a>
                    <c:if test="${newsPage.currPageNo gt 1}">
                        <a href="news.do?action=backnews&currentPageNo=${newsPage.currPageNo-1}">上一页</a>
                    </c:if>
                    <c:if test="${newsPage.currPageNo lt newsPage.totalPageCount} ">
                        <a href="news.do?action=backnews&currentPageNo=${newsPage.currPageNo+1}">下一页</a>
                    </c:if>
                    <a href="news.do?action=backnews&currentPageNo=${newsPage.totalPageCount}">末页</a>
                </p>
            </ul>
        </div>
            <%--	  <iframe src="console_element/bottom.html" scrolling="no" frameborder="0" width="947px" height="190px"></iframe>--%>
        <%@ include file="console_element/bottom.html" %>
    </div>
    <script type="text/javascript">
        function del(nid) {
            if (confirm("确定要删除吗？")) {
                location.href = "news.do?action=del&nid=" + nid;
            }
        }
    </script>
    </body>
</c:if>


</html>

	