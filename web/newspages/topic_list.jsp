<%@ page import="com.hisoft.news.javabean.Topic" %>
<%@ page import="java.util.List" %>
<%@ page language="java" pageEncoding="utf-8" %>
<%@ include file="controll_util.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <title>编辑主题--管理后台</title>
    <script src="js/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        function check() {
            var tname = document.getElementById("tname");

            if (tname.value == "") {
                alert("请输入主题名称！！");
                tname.focus();
                return false;
            }
            return true;
        }
    </script>
    <link href="css/admin.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div id="main">
    <div>
        <%--	    <iframe src="console_element/top.html" scrolling="no" frameborder="0" width="947px" height="180px"></iframe>--%>
        <%@ include file="console_element/top.html" %>
    </div>
    <div id="opt_list">
        <%--      	<iframe src="console_element/left.html" scrolling="no" frameborder="0" width="130px"></iframe>--%>
        <%@ include file="console_element/left.html" %>
    </div>
    <div id="opt_area">
        <ul class="classlist">
<%--            <c:forEach items="${applicationScope.topics}" var="topic">--%>
<%--                <li>${topic.tname}--%>
<%--                    &nbsp;&nbsp;&nbsp; <a href="newspages/topic_modify.jsp?tid=${topic.tid}&tname=${topic.tname}">修改</a>--%>
<%--                    &nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:;" onclick="del(${topic.tid})">删除</a>--%>
<%--                </li>--%>
<%--            </c:forEach>--%>


<%--            <%--%>
<%--                List<Topic> topics = (List<Topic>) request.getAttribute("topics");--%>
<%--                for (Topic topic : topics) {--%>
<%--            %>--%>
<%--            <li><%=topic.getTname()%>--%>
<%--                &nbsp;&nbsp;&nbsp;&nbsp; <a href="newspages/topic_modify.jsp?tid=<%=topic.getTid()%>&tname=<%=topic.getTname()%>">修改</a>--%>
<%--                &nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:;" onclick="del(<%=topic.getTid()%>)">删除</a>--%>
<%--            </li>--%>
<%--            <%--%>
<%--                }--%>
<%--            %>--%>

        </ul>
    </div>
    <%--	  <iframe src="console_element/bottom.html" scrolling="no" frameborder="0" width="947px" height="190px"></iframe>--%>
    <%@include file="console_element/bottom.html" %>
</div>
<script type="text/javascript">
	function del(tid) {
		if (confirm("你确定要删除吗？")){
			location.href="topic.do?action=del&tid="+tid;
		}
	}
    $(function(){
        // $.ajax({
        //     url:"topic.do",
        //     type:"get",
        //     data:"action=list",
        //     dataType:"json",
        //     success:function (result) {
        //         $(result).each(function () {
        //             var $li = $("<li>" +this.tname+
        //                 "&nbsp;&nbsp;&nbsp;&nbsp; <a href=\"newspages/topic_modify.jsp?tid="+this.tid+"&tname="+this.tname+"\">修改</a>\n" +
        //                 "&nbsp;&nbsp;&nbsp;&nbsp; <a href=\"javascript:;\" onclick=\"del("+this.tid+")\">删除</a>\n" +
        //                 "</li>");
        //             $("#opt_area>ul").append($li);
        //         })
        //     }
        // });

        //$.get(url,data,success,dataType);调用get请求发送ajax请求
        // $.get("topic.do","action=list",success,"json");
        //$.get(url,data,success,dataType);调用POST请求发送ajax请求
        // $.post("topic.do","action=list",success,"json");
        //$.getJSON(url,data,success);调用get请求发送ajax请求,并返回json格式的数据
        $.getJSON("topic.do","action=list",success);

    })

function success(result) {
    $(result).each(function () {
        var $li = $("<li>" + this.tname +
            "&nbsp;&nbsp;&nbsp;&nbsp; <a href=\"newspages/topic_modify.jsp?tid=" + this.tid + "&tname=" + this.tname + "\">修改</a>\n" +
            "&nbsp;&nbsp;&nbsp;&nbsp; <a href=\"javascript:;\" onclick=\"del(" + this.tid + ")\">删除</a>\n" +
            "</li>");
        $("#opt_area>ul").append($li);
    })
}

</script>
</body>
</html>	