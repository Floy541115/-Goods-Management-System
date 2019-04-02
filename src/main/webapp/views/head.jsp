<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'head.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="./UI/layui/css/layui.css">

  </head>
  
  <body>
  
      <div id="topPanel" > 
      	  <ul class="layui-nav" style="background-color:#FFE4BB;height:55px">
			  <li class="layui-nav-item">
			    <a href="goodViews/allGoods.jsp" style="color:black;text-decoration:none">主页</span></a>
			  </li>
			  <li class="layui-nav-item">
			  	<shiro:user>
			    	<a href="userViews/userInfo.jsp" style="color:black;text-decoration:none">个人中心<span class="layui-badge-dot"></span></a>
			  	</shiro:user>
			  </li>
			  <li class="layui-nav-item">
				  <shiro:user>
				    <a href="${pageContext.request.contextPath }/user/logout.action" style="color:black;text-decoration:none">退出</a>
				  </shiro:user>
			  </li>
			  <li class="layui-nav-item">
				  <shiro:guest>
				    <a href="views/login3.jsp" style="color:black;text-decoration:none">登录</a>
				  </shiro:guest>
			  </li>
			  <shiro:user>
			  	  <li class="layui-nav-item">
				  	  <a href="views/userViews/userList.jsp"style="color:black;text-decoration:none">用户管理</a>
				  	  <shiro:hasPermission name="user:add">
					  	  <dl class="layui-nav-child">
							  <dd><a href="views/userViews/addUser.jsp" style="color:black;text-decoration:none">添加用户</a></dd>
						  </dl>
					  </shiro:hasPermission>
				  </li>
			  </shiro:user>
			  <li class="layui-nav-item">
			  	 <a href="views/goodViews/allGoods.jsp" style="color:black;text-decoration:none">货物仓库</a>
			  </li>
		 </ul>
      </div>
      <script type="text/javascript" src="UI/js/jquery-1.11.0.js"></script>
	  <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
	  <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
	  <script type="text/javascript" src="./UI/layui/layui.js"></script>
      <script type="text/javascript"></script>
  </body>
</html>
