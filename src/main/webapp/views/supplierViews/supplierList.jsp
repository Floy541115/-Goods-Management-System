<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'supplierList.jsp' starting page</title>
    
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
    	layui.use('table', function(){
		  var table = layui.table;
		  
		  //第一个实例
		  table.render({
		    elem: '#userTable'
		    ,height: 312
		    ,url: '${pageContext.request.contextPath}/supplier/allSupplier.action' //数据接口
		    ,page: {theme:'#f0ad4e'} //开启分页
		    ,cols: [[ //表头
		      {field: 'supplierId', title: 'ID', width:80, sort: true, fixed: 'left'}
		      ,{field: 'supplierName', title: '供应商', width:210}
		      ,{field: 'address', title: '联系地址', width:160, sort: true}
		      ,{field: 'startTime', title: '开始合作时间', width:120} 
		      ,{field: 'personInCharge', title: '负责人', width: 100}
		      ,{field: 'phone', title: '联系方式', width: 120, sort: true}
		      ,{field: 'category', title: '供货种类', width: 80, sort: true}
		      ,{field: '', title: '', width: 80, align : 'center',toolbar : '#edit'}
		      ,{field: '', title: '', width: 80,align : 'center',toolbar : '#delete'}
		      ,{field: '', title: '', width: 135, sort: true}
		    ]]
		  });
		  
		  //监听工具条
		  table.on('tool(test)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
			  var data = obj.data; //获得当前行数据    json结构的数据
			  var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
			  var tr = obj.tr; //获得当前行 tr 的DOM对象
 			  // alert(data.userId);
			  if(layEvent === 'detail'){ //查看
			    //do somehing
			  } else if(layEvent === 'del'){ //删除
			    layer.confirm('真的删除行么', function(index){  //点击模态框确定按钮时
			      obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
			      layer.close(index);
			      url = "${pageContext.request.contextPath}/supplier/deleteBySupplierId.action";
			      var supplierId = data.supplierId;
			      $.post(url,{"supplierId":supplierId},function(rs){
			      	
			      })
			    });
			  } else if(layEvent === 'edit'){ //编辑
			    //do something
			    //同步更新缓存对应的值
			    var strHtml = '<div style="">用户名&ensp;&ensp;&ensp;&ensp;<input name="userName" value="'+data.userName+'"/></div>'
			    			+'<div style="margin-top:15px">联系方式&ensp;&ensp;<input name="phone" value="'+data.phone+'"/></div>'
			    			+'<div style="margin-top:15px">Email&ensp;&ensp;&ensp;&ensp;&ensp;<input name="email" value="'+data.email+'"/></div>'
			    			+'<div style="margin-top:15px">联系地址&ensp;&ensp;<input name="address" value="'+data.address+'"/></div>'
			    			+'<div style="margin-top:15px">角色&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;<input name="role" value="'+data.roleName+'"/></div>';
			    layer.confirm(strHtml, function(index){  //点击模态框确定按钮时
			       layer.close(index);
			       url = "${pageContext.request.contextPath}/user/updateUser.action";
			       var userId = data.userId;
			       var userName = document.getElementById("userName").value;
			       var phone = document.getElementById("phone").value;
			       var email = document.getElementById("email").value;
			       var address = document.getElementById("address").value;
			       var roleName = document.getElementById("roleName").value;
			       $.post(url,{"userId":userId,"userName":userName,"phone":phone,"email":email,"address":address,"roleName":roleName},function(rs){
			       	  /*obj.update({
			              userName: userName
			              ,title: '用户名'
			          });*/
			       })
			    });
			  }
			});
		});
    </script>
  </head>
  
  <body>
      <div style="padding:30px; height: 31px">
      	  <table id="userTable" lay-filter="test"></table>
      </div>
           <!-- ------------- toolbar 对应的模板     注意这里的script的type是  html/text--------------------------- -->
      <script type="text/html" id="edit">
  		  <a class="layui-btn layui-btn-xs" lay-event="edit" style="background-color:#f0ad4e">编辑</a>
	  </script>
	  <script type="text/html" id="delete">
  		  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" style="background-color:#f0ad4e">删除</a>
	  </script>
  </body>
</html>
