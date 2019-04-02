<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addSupplier.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="./UI/layui/css/layui.css">
	
	<script type="text/javascript" src="UI/js/jquery-1.11.0.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="./UI/layui/layui.js"></script>
    <script type="text/javascript">
    	layui.use('laydate', function(){
		   var laydate = layui.laydate;
		   //执行一个laydate实例
		   laydate.render({
		      elem: '#time' //指定元素
		      ,theme: '#f0ad4e'
		   });
		});
		 layui.use('element', function(){
			  var element = layui.element;
			  //…
		   });
		$(document).ready(function(){
		   	   $.ajax({
		   	   	    url:"${pageContext.request.contextPath}/allRoles.action",
		   	   	    type:"post",
		   	   	    data:{},
		   	   	    dataType:"json",
		   	   	    success:function(rs){
		   	   	    	var strHtml = "";
		   	   	    	for(var i=0; i<rs.length; i++){
		   	   	    		strHtml += "<option value=\""+(i+1)+"\">"+rs[i].roleName+"</option>"; 
		   	   	    		//[{"roleId":1,"roleName":"客服"},{"roleId":3,"roleName":"客服组长"},{"roleId":4,"roleName":"导购员"},{"roleId":5,"roleName":"导购组长"},
		   	   	    	}
		   	   	    	document.getElementById("roleList").innerHTML = strHtml;
		   	   	    },
		   	   	    error:function(XMLHttpRequest,textStatus,errorThrown){
						alert("status:  "+XMLHttpRequest.status+"    readyState: "+XMLHttpRequest.readyState
							+"\n responseText:  "+XMLHttpRequest.responseText);
					}
		   	   });
		   });
    </script>

  </head>
  
  <body>
      <div>
      	  <form action="insertSupplier.action" method = "post">
      	  	  <div style="width:500px;margin-left:350px;margin-top:50px">
	      	  	  <div class="input-group">
				     <span class="input-group-addon" id="sizing-addon2" style="background-color:#FFE4BB">
				     	 <i class="layui-icon layui-icon-username"></i>供应商   
				     </span>
				     <input type="text" class="form-control" placeholder="SupplierName" aria-describedby="sizing-addon2" name="supplierName">
				 </div>
				 <div class="input-group" style="margin-top:20px">
				     <span class="input-group-addon" id="sizing-addon2"  style="background-color:#FFE4BB">
				     	 <i class="layui-icon layui-icon-password"></i>开始合作日期
				     </span>
				     <input type="text" class="form-control" name="startTime" id="time" placeholder="cooperateStartTime" aria-describedby="sizing-addon2">
				 </div>
	      	  </div>
	      	  <div style="margin-left:200px;margin-top:30px">
	      	  	  <div class="input-group" style="width:400px">
				     <span class="input-group-addon" id="sizing-addon2" style="background-color:#FFE4BB">
				     	 <i class="layui-icon layui-icon-friends"></i>负责人
				     </span>
				     <input type="text" class="form-control" name="personInCharge" placeholder="personInCharge" aria-describedby="sizing-addon2">
				 </div>
				 <div class="input-group" style="width:400px;margin-left:430px;margin-top:-33px">
				     <span class="input-group-addon" id="sizing-addon2"  style="background-color:#FFE4BB">
				     	 <i class="layui-icon layui-icon-cellphone-fine"></i>联系方式
				     </span>
				     <input type="text" class="form-control" name="phone" placeholder="Phone" aria-describedby="sizing-addon2">
				 </div>
				 <div class="input-group" class="input-group" style="width:830px;margin-top:20px">
				     <span class="input-group-addon" id="sizing-addon2"  style="background-color:#FFE4BB">
				     	 <i class="layui-icon layui-icon-location"></i>联系地址
				     </span>
				     <input type="text" class="form-control" name="address" placeholder="Address" aria-describedby="sizing-addon2">
				 </div>
				 <div class="input-group" class="input-group" style="margin-top:20px;margin-left:350px">
	      	  	    <span type="button" class="btn btn-default dropdown-toggle" style="background-color:#FFE4BB" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="layui-icon layui-icon-friends"></i>供货种类</span>
				    <select name="category" lay-verify="required" id="roleList" style="width:370px;height:36px;">
				    </select>
	             </div>
	      	  </div>
	      	  <input type="submit" value="添加供应商" class="btn btn-warning" style="width:200px;margin-top:20px;margin-left:500px"/>
      	  </form>
      </div>
  </body>
</html>
