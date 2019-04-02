<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'zzzz.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		.font{
			color:#778899;
			font-size:24px;
			font-weight:bold
		}
	</style>
  </head>
  
  <body>
        <div class="font" style="text-aligne:center">
        	对不起，当前用户没有此操作权限！
        </div>
        <script type="text/javascript" src="UI/js/jquery-1.11.0.js"></script>
       <script type="text/javascript">
 
       </script>
  </body>
</html>
