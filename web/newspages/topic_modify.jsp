<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="controll_util.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
<title>新闻中国</title>
   <script type="text/javascript">
		function check(){
			var tname = document.getElementById("tname");
	
			if(tname.value == ""){
				alert("请输入主题名称！！");
				tname.focus();
				return false;
			}		
			return true;
		}
	</script>
    <link href="css/admin.css" rel="stylesheet" type="text/css" />
</head>
<body>
  <div id="main">
      <div>
<%--	    <iframe src="console_element/top.html" scrolling="no" frameborder="0" width="947px" height="180px"></iframe>--%>
	<%@ include file="console_element/top.html"%>
	  </div> 
      <div id="opt_list">
<%--      	<iframe src="console_element/left.html" scrolling="no" frameborder="0" width="130px"></iframe>--%>
	<%@ include file="console_element/left.html"%>
      </div> 
	  <div id="opt_area">
	    <h1 id="opt_type"> 修改主题： </h1>
		  <%
			  String error = (String)request.getAttribute("error");
		  %>
	    <form action="topic.do" method="post" onsubmit="return check()" >
	      <p>
	        <label> 主题名称 </label>
	        <input name="tname" type="text" class="opt_input" value="<%=request.getParameter("tname")%>" />
			  <span style="color: red"><%=error==null?"":error%></span>
	      </p>
			<input name="tid" type="hidden" value="<%=request.getParameter("tid")%>">
			<input name="action" type="hidden" value="updatetopic">
			<input type="submit" value="提交" class="opt_sub" />
	      <input type="reset" value="重置" class="opt_sub" />
	    </form>
	  </div>
<%--	  <iframe src="console_element/bottom.html" scrolling="no" frameborder="0" width="947px" height="190px"></iframe>--%>
	  <%@include file="console_element/bottom.html"%>
  </div>
</body>
</html>	