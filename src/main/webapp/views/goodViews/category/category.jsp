<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'category.jsp' starting page</title>
    
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="./UI/layui/css/layui.css">
  </head>
  
  <body>
  	     	   
  	   <div>
			<div class="modal fade" id="addCategory" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="exampleModalLabel">添加商品类型</h4>
			      </div>
			      <div class="modal-body">
			        <form>
			          <div class="form-group">
			            <label for="category-name" class="control-label">类型名:</label>
			            <input type="text" class="form-control" id="categoryName">
			          </div>
			        </form>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			        <button  class="btn btn-warning" id="submitCategory" type="button" onclick="javascript:addCategory()">确定</button>
			      </div>
			    </div>
			  </div>
			</div>
  	    </div>
  	     <div>
			<div class="modal fade" id="addSubCategory" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="exampleModalLabel">添加商品类型</h4>
			      </div>
			      <div class="modal-body">
			        <form>
			          <div class="form-group">
			            <label for="category-name" class="control-label">类型名:</label>
			            <input type="text" class="form-control" id="subCategoryName">
			          </div>
			        </form>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			        <button  class="btn btn-warning" id="submitSubCategory" type="button" onclick="javascript:addSubCategory()">确定</button>
			      </div>
			    </div>
			  </div>
			</div>
  	    </div>
  	   <div>
  	   	   <div class="layui-collapse" style="width:560px;margin-left:300px;margin-top:50px" >
			  <div id="categoryList" ></div>
			  <div>
			  	  <a class="layui-colla-item" href="" style="text-decoration:none;" data-toggle="modal" data-target="#addCategory">
				    <div class="layui-colla-title" style="background-color:#FFE4BB;border-color:#e6e6e6">
				    	<i class="layui-icon layui-icon-add-1" style="margin-left:200px"></i>添加商品种类
				    </div>
				  </a>
			  </div>
			  <input style="display:none" id="categoryId" >
		   </div>
  	   </div>

       <script type="text/javascript" src="./UI/js/jquery-1.11.0.js"></script>
       <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
 	   <script src="./UI/bootstrap/js/bootstrap.min.js"></script >
	   <script type="text/javascript" src="./UI/layui/layui.js"></script>
	   <script type="text/javascript">
	   	   layui.use('element', function(){
		      var element = layui.element;
		   });
		   function op(bt){
		   alert(bt.id);
		   	   $("#addSubCategory").modal('show');
		   	   $("#categoryId").attr("value",bt.id);
		   }
		   function addCategory(){
		        var categoryName = document.getElementById("categoryName").value;
		   	    $.ajax({
		   	   	   url:"${pageContext.request.contextPath}/addCategory.action",
		   	   	   data:{"categoryName":categoryName},
		   	   	   dataType:"json",
		   	   	   type:"post",
		   	   	   success:function(rs){
		   	   	   	  strHtml = "<div class=\"layui-colla-item\">"
				    			 +"<h2 class=\"layui-colla-title\" style=\"background-color:#FFE4BB\">"+rs.category+"</h2>"
				       			 +"<div class=\"layui-colla-content layui-show list-group-item-warning\">"
			 			         +"<div id=\""+rs.category+"\"></div>"
						         +"<div>"
						  	     +"<button class=\"list-group-item list-group-item-warning\" id=\""+rs.category+"\" onclick=\"javascript:op(this)\">" // +"data-toggle=\"modal\" data-target=\"#addSubCategory\">"
							  	 +"<i class=\"layui-icon layui-icon-add-1\" style=\"color:#FFAD33;margin-left:180px\"></i>&ensp;&ensp;&ensp;&ensp;添加子类型"
							  	 +"</button></div></div></div>";
					   var div = document.getElementById("categoryList");
					   div.innerHTML = div.innerHTML + strHtml;
		   	   	   },
		   	   	    error:function(XMLHttpRequest,textStatus,errorThrown){
						alert("status:  "+XMLHttpRequest.status+"    readyState: "+XMLHttpRequest.readyState
							+"\n responseText:  "+XMLHttpRequest.responseText);
					}
		   	    });
		   }
		   function addSubCategory(){
		       var subCategoryName = document.getElementById("subCategoryName").value;
		       var categoryName = document.getElementById("categoryId").value;
		   	   $.ajax({
		   	   	   url:"${pageContext.request.contextPath}/addSubCategory.action",
		   	   	   data:{"categoryName":categoryName,"subCategoryName":subCategoryName},
		   	   	   dataType:"json",
		   	   	   type:"post",
		   	   	   success:function(rs){
		   	   	   	   var categoryId = "";
		   	   	   	   strHtml = "<a href=\"#\" class=\"list-group-item list-group-item-warning\">"
							  	 +"&ensp;&ensp;&ensp;&ensp;<i class=\"layui-icon layui-icon-radio\" style=\"color:#FFAD33\"></i>&ensp;&ensp;&ensp;&ensp;"+subCategoryName
							  	 +"</a>";
					   var div = document.getElementById(categoryName);
					   div.innerHTML = div.innerHTML + strHtml;
		   	   	   }
		   	    });
		   }
		   $(document).ready(function(){
		   	   $.ajax({
		   	   	   url:"${pageContext.request.contextPath}/allCategory.action",
		   	   	   data:{},
		   	   	   dataType:"json",
		   	   	   type:"post",
		   	   	   success:function(rs){
		   	   	   	   var field = rs.field;
		   	   	   	   var strHtml = "";
		   	   	   	   for(var i=0; i<field.length;i++){
		   	   	   	       var f = field[i];
		   	   	   	   	   strHtml +=  "<div class=\"layui-colla-item\">"
				    			 +"<h2 class=\"layui-colla-title\" style=\"background-color:#FFE4BB\">"+f+"</h2>"
				       			 +"<div class=\"layui-colla-content layui-show list-group-item-warning\">"
			 			         +"<div id=\""+f+"\">";
			 			   var innerStrHtml = "";
						   for(var j=0; j<rs[f].length; j++){
						    	innerStrHtml +=  "<a href=\"#\" class=\"list-group-item list-group-item-warning\">"
							  	 				+"&ensp;&ensp;&ensp;&ensp;<i class=\"layui-icon layui-icon-radio\" style=\"color:#FFAD33\"></i>&ensp;&ensp;&ensp;&ensp;"+rs[f][j]
							  	 				+"</a>";
						   }
						   strHtml += innerStrHtml+"</div>"
						         +"<div>"
						  	     +"<button class=\"list-group-item list-group-item-warning\" id=\""+f+"\" onclick=\"javascript:op(this)\">" // +"data-toggle=\"modal\" data-target=\"#addSubCategory\">"
							  	 +"<i class=\"layui-icon layui-icon-add-1\" style=\"color:#FFAD33;margin-left:180px\"></i>&ensp;&ensp;&ensp;&ensp;添加子类型"
							  	 +"</button></div></div></div>";
		   	   	   	   }
					   document.getElementById("categoryList").innerHTML = strHtml;
		   	   	   }
		   	    });
		   });
		   

	   </script>
  </body>
</html>
