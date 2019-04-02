<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'loginFail.jsp' starting page</title>
    
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
			font-size:20px;
			font-weight:bold
		}
	</style>
  </head>
  
  <body>
       <div class="font">
       	         登录失败，若您已拥有账户，请确认登录名和密码正确，若您还未拥有账户请联系店长
       </div>
       <script type="text/javascript" src="UI/js/jquery-1.11.0.js"></script>
  </body>
</html>
