<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'rolePermission.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="./UI/layui/css/layui.css">
	<style type="text/css">
		.font{
			color:#778899;
			font-size:16px;
			font-weight:bold
		}
	</style>
  </head>
  
  <body>
  	  <jsp:include page="head.jsp" flush="true"></jsp:include>
  	  <a id="rpList"></a>
  	  <div>
  	      <div style="width:1040px;height:auto;background-color:#FFE4BB;margin-top:30px;margin-left:100px" id="rolePmsListDiv">
		  	  <div style="margin-left:480px;padding-top:10px;height:40px" class="font">角色及角色权限</div>
		  	  <div> <table id="rolePmsList" lay-filter="test"></table></div>
		  </div>
  	  </div>
  	  <div style="margin-left:400px">
  	  	  <shiro:hasPermission name="rp:add">
  	  	  	   <button class="btn btn-warning" onclick="javascript:createRole()">新建角色</button>
  	  	  </shiro:hasPermission>
  	  	  <shiro:hasPermission name="rp:add">
  	  	  	    <button class="btn btn-warning" onclick="javascript:createPermission()">新建权限</button>
  	  	  </shiro:hasPermission>
  	  </div >
  	  <div id="layeiDiv">
  	  	 <div id="layUiCreateRole" style="display:none">
	  	 	角色名:&ensp;&ensp; <input name="lroleName" id="lroleName">
	  	 </div>
	  	 <div id="layUiCreatePermission" style="display:none">
	  	 	权限描述: <input name="ldescription" id="ldescription"><br><br>
	  	 	resourceCode: <input name="lresourceCode" id="lresourceCode">
	  	 </div>
	  	 <div id="layUiaddPR" style="display:none">
	  	     <div class="input-group" style="margin-top:20px;margin-top:20px">
		      	  	 添加角色权限:
					  <select name="rp" lay-verify="required" id="rp" style="width:431px;height:34px;margin-left:30px;margin-top:-34px">
					      <option value=""></option>
					 </select>
		         </div>
	  	 </div>
  	  </div>  
      <script type="text/html" id="edit">
  		   <shiro:hasPermission name="rp:edit">
		       <a class="layui-btn layui-btn-xs" lay-event="edit" style="background-color:#f0ad4e";text-decoration:none>编辑</a>
		   </shiro:hasPermission>
	  </script>
	  <script type="text/html" id="delete">
		  <shiro:hasPermission name="rp:delete">
  		  	  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delRP" style="background-color:#f0ad4e;text-decoration:none">删除权限</a>
		  </shiro:hasPermission> 
	  </script>
	  <script type="text/html" id="deleteRole">
		   <shiro:hasPermission name="rp:delete"> 
  		  	  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delRole" style="background-color:#f0ad4e;text-decoration:none">删除角色</a>
		   </shiro:hasPermission>
		   <shiro:hasPermission name="rp:delete">
		  	  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="addRP" style="background-color:#f0ad4e;text-decoration:none">添加权限</a>
		   </shiro:hasPermission>
	  </script>
      <script type="text/javascript" src="./UI/js/jquery-1.11.0.js"></script>
	  <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
	  <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
	  <script type="text/javascript" src="./UI/layui/layui.js"></script>
      <script type="text/javascript">
          layui.use('element', function(){
			  var element = layui.element;
		  });
          $(document).ready(function(){
         	  rolePermissionTable();
          });
          function allPermission(){
          	    $.ajax({
		   	   	    url:"${pageContext.request.contextPath}/rolePermission/findAllPermission.action",
		   	   	    type:"post",
		   	   	    data:{},
		   	   	    dataType:"json",
		   	   	    success:function(rs){
		   	   	    	var strHtml = "";
		   	   	    	//"field":["玩具","食品","婴儿用品","生活用品","文具","电器"],
		   	   	    	for(var i=0; i<rs.length; i++){
		   	   	    		strHtml += "<option value=\""+rs[i].permissionId+"\">"+rs[i].description+"</option>";
		   	   	    	}
		   	   	    	document.getElementById("rp").innerHTML = strHtml;
		   	   	    },
		   	   	    error:function(XMLHttpRequest,textStatus,errorThrown){
						alert("status:  "+XMLHttpRequest.status+"    readyState: "+XMLHttpRequest.readyState
							+"\n responseText:  "+XMLHttpRequest.responseText);
					}
		   	   });
          }
      	  function rolePermissionTable(){
			   layui.use('table', function(){
			       var table = layui.table;
				   table.render({
				      elem: '#rolePmsList'
				      ,height: 342
				      ,url: '${pageContext.request.contextPath}/rolePermission/findAllRoleAndPms.action' //数据接口
				      ,page: {theme:'#f0ad4e'} //开启分页
				      ,cols: [[ //表头
				         {field: 'roleId', title: '角色ID', width:120}
				        ,{field: 'roleName', title: '角色名', width:110}
				        ,{field: '', title: '', width: 220,align : 'center',toolbar : '#deleteRole'}
				        ,{field: 'permissionId', title: '', width:0,hide:true}
				        ,{field: 'resourceCode', title: '权限', width:210}
				        ,{field: 'description', title: '权限描述', width:180} 
				        ,{field: '', title: '', width: 80, align : 'center',toolbar : '#edit'}
		      			,{field: '', title: '', width: 95,align : 'center',toolbar : '#delete'}
				     ]]
				     ,done: function(res, curr, count){
				          alarmTableRowSpan("roleId",1);   
				          alarmTableRowSpan("roleName",2);
				          alarmTableRowSpan("deleteRole",8);
				     }
				  });
				  table.on('tool(test)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
					  var data = obj.data; //获得当前行数据    json结构的数据
					  var layEvent = obj.event; 
					  var tr = obj.tr;  
					  if(layEvent === 'delRP'){  
					      layer.confirm('真的删除该角色的权限吗', function(index){  //点击模态框确定按钮时
						      obj.del(); 
						      layer.close(index);
						      var url = "${pageContext.request.contextPath}/rolePermission/deleteRolePermissionByRPId.action";
					      	  var roleId = data.roleId;
					      	  var permissionId = data.permissionId;
						      $.post(url,{"roleId":roleId,"permissionId":permissionId},function(rs){
						      });
						  });
					  }else if(layEvent === 'delRole'){
					  	   layer.confirm('真的删除该角色吗', function(index){  //点击模态框确定按钮时
						      obj.del(); 
						      layer.close(index);
						      var url = "${pageContext.request.contextPath}/rolePermission/deleteRole.action";
					      	  var roleId = data.roleId;
						      $.post(url,{"roleId":roleId,"permissionId":permissionId},function(rs){
						      });
						  });
					  }else if(layEvent === 'edit'){
					   /*	  table.on('edit(test)', function(obj){ //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
									  console.log(obj.value); //得到修改后的值
									  console.log(obj.field); //当前编辑的字段名
									  console.log(obj.data); //所在行的所有相关数据  
								  });*/
					  
					  }else if(layEvent === 'addRP'){
					      var roleId = data.roleId;
					      var url = '${pageContext.request.contextPath}/rolePermission/insertRolePermission.action';
					      allPermission();
					  	  layer.open({  //layer已在前边定义
							    title:['添加权限'],
							    skin:'body .demo-class .layui-layer-title{background:#FFE4BB;}',
							    area:['650px','270px'],
						   		content:$("#layUiaddPR"),   //   './views/addSupplier.jsp' 若嵌入页面，应该设置type=2
						   		yes: function(index,layero){    //点击“确认”按钮响应  .children('option:selected').val()
						   			var permissionId = $("#rp").children('option:selected').val();
						   			layer.close(index);
						   		    $.post(url,{'roleId':roleId,'permissionId':permissionId},function(rs){
						   		        rolePermissionTable();
						   		    });
						   		}
						    });
					  }
			  	  });
			  });
      	  }
      	  
      	  //合并单元格   https://fly.layui.com/jie/27432/
      	  /*
      	   * @param fieldName  要合并列的field属性值
 		   * @param index 列索引，从1开始
      	  */
      	 function alarmTableRowSpan(fieldName, index) {    
		    var fixedNode = document.getElementsByClassName("layui-table-body")[0];
		    if (!fixedNode) {
		        return false;
		    }
		    var child = fixedNode.getElementsByTagName("td");
		    var childFilterArr = [];
		    // 获取data-field属性为fieldName的td
		    for (var i = 0; i < child.length; i++) {
		        if (child[i].getAttribute("data-field") == fieldName) {
		            childFilterArr.push(child[i]);
		        }
		    }
		    // 获取td的个数和种类
		    var childFilterTextObj = {};
		    for (var i = 0; i < childFilterArr.length; i++) {
		        var childText = childFilterArr[i].textContent;
		        if (childFilterTextObj[childText] == undefined) {
		            childFilterTextObj[childText] = 1;
		        } else {
		            var num = childFilterTextObj[childText];
		            childFilterTextObj[childText] = num * 1 + 1;
		        }
		    }
		    // 给获取到的td设置合并单元格属性
		    for (var key in childFilterTextObj) {
		        var tdNum = childFilterTextObj[key];
		        var canRowSpan = true;
		        for (var i = 0; i < childFilterArr.length; i++) {
		            if (childFilterArr[i].textContent == key) {
		                if (canRowSpan) {
		                    childFilterArr[i].setAttribute("rowspan", tdNum);
		                    canRowSpan = false;
		                } else {
		                    childFilterArr[i].style.display = "none";
		                }
		            }
		        }
		    }
		  }
		  $.ajaxSetup({
	    	statusCode:{
	    		499:function(data){
	    			window.location ="zzzz.jsp";
	    		}
	    	}
	    })
					function createRole(){
				        layui.use('layer', function(){
				            
							var url = '${pageContext.request.contextPath}/rolePermission/insertRole.action';
							//var layer = layui.layer;
							layer.open({  //layer已在前边定义
							    title:['新建角色'],
							    skin:'body .demo-class .layui-layer-title{background:#FFE4BB;}',
							    area:['400px','190px'],
						   		content:$("#layUiCreateRole"),   //   './views/addSupplier.jsp' 若嵌入页面，应该设置type=2
						   		yes: function(index,layero){    //点击“确认”按钮响应
						   			var roleName = $("#lroleName").val();
						   			layer.close(index);
						   		    $.post(url,{"roleName":roleName},function(rs){
						   		    	 if(rs == "success"){
						   		    	    alert("角色创建成功")
						   		    	 	rolePermissionTable();
						   		    	 }else if(rs == "faild"){
						   		    	 	alert("角色创建失败");
						   		    	 }
						   		         
						   		     });
						   		}
						    });
					   });
		  			}
		  			function createPermission(){
		  				layui.use('layer', function(){
							var url = "${pageContext.request.contextPath}/rolePermission/insertPermission.action";
							layer.open({  //layer已在前边定义
							    title:['新建权限'],
							    skin:'body .demo-class .layui-layer-title{background:#FFE4BB;}',
							    area:['400px','270px'],
						   		content:$("#layUiCreatePermission"),   //   './views/addSupplier.jsp' 若嵌入页面，应该设置type=2
						   		yes: function(index,layero){    //点击“确认”按钮响应
						   			var resourceCode = $("#lresourceCode").val();
						   			var description = $("#ldescription").val();
						   			layer.close(index);
						   		    $.post(url,{'resourceCode':resourceCode,'description':description},function(rs){
						   		          if(rs == "success"){ 
						   		          	  alert("权限创建成功");
						   		          }else if(rs == "faild"){
						   		          	  alert("权限创建失败");
						   		          }
						   		    });
						   		}
						    });
					   });
		  			}
      </script>
  </body>
</html>
