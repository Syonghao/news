<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page import="com.hisoft.news.javabean.Comments" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <base href="<%=basePath%>"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>新闻中国</title>
    <link href="css/read.css" rel="stylesheet" type="text/css"/>
    <script src="js/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        function check() {
            var cauthor = document.getElementById("cauthor");
            var content = document.getElementById("ccontent");
            if (cauthor.value == "") {
                alert("用户名不能为空！！");
                return false;
            } else if (content.value == "") {
                alert("评论内容不能为空！！");
                return false;
            }
            return true;
        }
    </script>
</head>
<%
    News n = (News) request.getAttribute("news");
    List<Comments> commentsList = n.getCommentsList();
%>
<body>
<div id="header">
    <div id="top_login">
        <label> 登录名 </label>
        <input type="text" id="uname" value="" class="login_input"/>
        <label> 密&#160;&#160;码 </label>
        <input type="password" id="upwd" value="" class="login_input"/>
        <input type="button" class="login_sub" value="登录" onclick="login()"/>
        <label id="error"> </label>
        <a href="index.jsp" class="login_link">返回首页</a> <img src="images/friend_logo.gif" alt="Google"
                                                             id="friend_logo"/></div>
    <div id="nav">
        <div id="logo"><img src="images/logo.jpg" alt="新闻中国"/></div>
        <div id="a_b01"><img src="images/a_b01.gif" alt=""/></div>
        <!--mainnav end-->
    </div>
</div>
<div id="container">
    <%@ include file="index-elements/index_left_bar.jsp" %>
    <div class="main">
        <div class="class_type"><img src="images/class_type.gif" alt="新闻中心"/></div>
        <div class="content">
            <ul class="classlist">
                <table width="80%" align="center">
                    <tr width="100%">
                        <td colspan="2" align="center">${news.ntitle}
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr/>
                        </td>
                    </tr>
                    <tr>
                        <td align="center">${news.ncreateDate}
                        </td>
                        <td align="left">${news.nauthor}
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center"></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            ${news.ncontent}
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr/>
                        </td>
                    </tr>
                </table>
            </ul>
            <ul class="classlist">
                <table width="80%" align="center" id="commentstable">

                   <c:forEach items="${news.commentsList}" var="comments" >
                    <tr>
                        <td colspan="2"> ${comments.ccontent}</td>
                        <td colspan="2"> ${comments.cauthor}</td>
                        <td colspan="2"> ${comments.cdate}</td>
                    </tr>
                    <tr>
                        <td colspan="6">
                            <hr/>
                        </td>
                    </tr>
                   </c:forEach>

                </table>
            </ul>
            <ul class="classlist">
                <form action="#" id="commentsform" method="post">
                    <input type="hidden" name="cnid" value="${news.nid}"/>
                    <table width="80%" align="center">
                        <tr>
                            <td> 评 论</td>
                        </tr>
                        <tr>
                            <td> 用户名：</td>
                            <td><input id="cauthor" readonly name="cauthor" value="<c:out value='${sessionScope.user}' default="这家伙很懒什么也没留下"/>" />
                                IP：<input name="cip" value="${pageContext.request.remoteAddr}" readonly/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><textarea id="ccontent" name="ccontent" cols="70" rows="10"></textarea>
                            </td>
                        </tr>
                        <td><input id="csubmit" value="发  表" type="button"/>
                        </td>
                    </table>
                </form>
            </ul>
        </div>
    </div>
</div>
<div id="footer">
<%--    <iframe src="index-elements/index_bottom.html" scrolling="no" frameborder="0" width="947px" height="190px"></iframe>--%>
  <%@include file="index-elements/index_bottom.html"%>
</div>
<script type="text/javascript">
    $("#csubmit").click(function () {
       // console.log( $("#commentsform").serializeArray());
        $.post("comments.do",$("#commentsform").serialize(),function (result) {
            if(result.state == 200){
                //添加成功
                var $tr = $(" <tr>\n" +
                        " <td colspan=\"2\">"+$("#contents").val()+"</td>\n" +
                        "<td colspan=\"2\">"+$("#cauthor").val()+"</td>\n" +
                        "<td colspan=\"2\"> "+new Date()+"</td>\n" +
                        " </tr>\n" +
                        "  <tr>\n" +
                        "<td colspan=\"6\">\n" +
                        "<hr/>\n" +
                        "</td>\n" +
                        "</tr>");
                    $("#commentstable").prepend($tr);
            }else if(result.state == 201){
                alert(result.message);
            }
        },"json");
    })

</script>
</body>
</html>
