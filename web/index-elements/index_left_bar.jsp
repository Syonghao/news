<%@ page import="com.hisoft.news.javabean.News" %>
<%@ page import="com.hisoft.news.dao.NewsDao" %>
<%@ page import="com.hisoft.news.dao.impl.NewsDaoImpl" %>
<%@ page import="com.hisoft.news.util.JdbcUtil" %>
<%@ page import="com.hisoft.news.service.NewsService" %>
<%@ page import="com.hisoft.news.service.impl.NewsServiceImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    NewsService newsService = new NewsServiceImpl();
    List<News> list1 = newsService.findNewsByTname("国内");
    List<News> list2 = newsService.findNewsByTname("国际");
    List<News> list3 = newsService.findNewsByTname("娱乐");
%>
<div class="sidebar">
    <h1><img src="images/title_1.gif" alt="国内新闻"/></h1>
    <div class="side_list">
        <ul>
<%--            <c:forEach items="${list1}" var="news">--%>
<%--                <li><a href='news.do?action=readnews&nid=${news.nid}'><b>${news.ntitle}--%>
<%--                </b></a></li>--%>
<%--            </c:forEach>--%>
            <%
                for (News news : list1) {
            %>
            <li><a href='#'><b><%=news.getNtitle()%>
            </b></a></li>
            <%
                }
            %>

        </ul>
    </div>
    <h1><img src="images/title_2.gif" alt="国际新闻"/></h1>
    <div class="side_list">
        <ul>
<%--            <c:forEach items="${list2}" var="news">--%>
<%--                <li><a href='news.do?action=readnews&nid=${news.nid}'><b>${news.ntitle}--%>
<%--                </b></a></li>--%>
<%--            </c:forEach>--%>
            <%
                for (News news : list2) {
            %>
            <li><a href='#'><b><%=news.getNtitle()%>
            </b></a></li>
            <%
                }
            %>
        </ul>
    </div>
    <h1><img src="images/title_3.gif" alt="娱乐新闻"/></h1>
    <div class="side_list">
        <ul>
<%--            <c:forEach items="${list3}" var="news">--%>
<%--                <li><a href='news.do?action=readnews&nid=${news.nid}'><b>${news.ntitle}--%>
<%--                </b></a></li>--%>
<%--            </c:forEach>--%>
            <%
                for (News news : list3) {
            %>
            <li><a href='#'><b><%=news.getNtitle()%>
            </b></a></li>
            <%
                }
            %>
        </ul>
    </div>
</div>
