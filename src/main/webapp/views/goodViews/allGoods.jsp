<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'allGoods.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="./UI/layui/css/layui.css">
  </head>
  
  <body>
  	  <jsp:include page="../head.jsp" flush="true"></jsp:include>
      
      <div>
      	   <div style="width:1000px;margin-left:150px;margin-top:30px">
      	      <nav class="navbar navbar-default">
				  <div class="container-fluid">
					    <!-- Brand and toggle get grouped for better mobile display -->
					    <div class="navbar-header">
					      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					        <span class="sr-only">Toggle navigation</span>
					        <span class="icon-bar"></span>
					        <span class="icon-bar"></span>
					        <span class="icon-bar"></span>
					      </button>
					    </div>
					
					    <!-- Collect the nav links, forms, and other content for toggling -->
					    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" style="margin-left:200px">
					      <ul class="nav navbar-nav">
					        <li class="dropdown">
					          <!-- <select href="#" class="dropdown-toggle" data-toggle="dropdown" style="margin-right:60px" role="button" aria-haspopup="true" aria-expanded="false">Find By Category<span class="caret"></span></select> -->
					          Search By Category <select name="category" onchange="javascript:searchByCategory()" lay-verify="required" id="category" class="dropdown-toggle" style="width:230px;height:32px;margin-right:40px;margin-top:8px" aria-haspopup="true" aria-expanded="false">
							      <option value=""></option>
							 </select>
					        </li>
					      </ul>
					      <form class="navbar-form navbar-left" action="javascript:searchByGoodName()">
					        <div class="form-group">
					          <input type="text" class="form-control" id="goodName" name="goodName" placeholder="Search By GoodName">
					        </div>
					        <button type="submit" class="btn btn-default">Search</button>
					      </form>
					    </div> 
				  </div> 
				</nav>
				 <div style="width:1200px;margin-left:-70px">
		      	     <table id="goodList" lay-filter="test"></table>
		      	 </div>
		      	 <shiro:hasPermission name="good:add">
		      	 	 <button class="btn btn-warning" style="width:200px;margin-top:30px;margin-left:300px" onclick="javascript:window.location='views/goodViews/addGoods.jsp'">添加货物</button>
		      	 </shiro:hasPermission>
      	   </div>
      </div>
      
        <div id="layer">
        	<div id="inputLayer" style="display:none">
        		请输入入库数量:&ensp;&ensp;<input name="inputNumber" id="inputNumber">
        	</div>
        	<div id="outputLayer" style="display:none">
        		请输入出库数量:&ensp;&ensp;<input name="outputNumber" id="outputNumber">
        	</div>
        </div>

		<script type="text/html" id="layUiDeleteGood">
			<shiro:hasPermission name="good:delete">
				<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="deleteGood" style="background-color:#f0ad4e;text-decoration:none">删除货物</a>
			</shiro:hasPermission>
		</script> 
		<script type="text/html" id="layUiInputGood">
			<shiro:hasPermission name="good:input">
				<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="inputGood" style="background-color:#f0ad4e;text-decoration:none">货物入库</a>
			</shiro:hasPermission>		
		</script>
		<script type="text/html" id="layUiOutputGood">
			<shiro:hasPermission name="good:output">
				<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="outputGood" style="background-color:#f0ad4e;text-decoration:none">货物出库</a>
			</shiro:hasPermission>		
		</script>
		<script type="text/javascript" src="UI/js/jquery-1.10.2.js"></script>
	    <script type="text/javascript" src="UI/js/jquery-1.11.0.js"></script>
	    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
	    <script type="text/javascript" src="./UI/layui/layui.js"></script>
		<script type="text/javascript">
		     
			$(document).ready(function(){
			   layui.use('table', function(){
				  var table = layui.table;
				  $.ajax({
		   	   	    url:"${pageContext.request.contextPath}/category/allCategory.action",
		   	   	    type:"post",
		   	   	    data:{},
		   	   	    dataType:"json",
		   	   	    success:function(rs){
		   	   	    	var strHtml = "";
		   	   	    	//"field":["玩具","食品","婴儿用品","生活用品","文具","电器"],
		   	   	    	for(var i=0; i<rs.field.length; i++){
		   	   	    		strHtml += "<option value=\""+rs.field[i]+"\">"+rs.field[i]+"</option>";
		   	   	    	}
		   	   	    	document.getElementById("category").innerHTML = strHtml;
		   	   	    },
		   	   	    error:function(XMLHttpRequest,textStatus,errorThrown){
						alert("status:  "+XMLHttpRequest.status+"    readyState: "+XMLHttpRequest.readyState
							+"\n responseText:  "+XMLHttpRequest.responseText);
					}
		   	      });
				  searchByCategory();
			   });
			    
			});
			function searchByCategory(){
			   layui.use('table', function(){
			       var table = layui.table;
				   var category = $("#category").children('option:selected').val();
				   var url = encodeURI('${pageContext.request.contextPath}/good/findGoodByCategory.action?category='+category);
				   goodTable(table,url);
			   });
			    
			}
			function searchByGoodName(){
			    layui.use('table', function(){
			       var table = layui.table;
				   var goodName = $("#goodName").val();
				   var url = encodeURI('${pageContext.request.contextPath}/good/findGoodByName.action?goodName='+goodName);
				   goodTable(table,url);
			   });
				 
		   }
		   function goodTable(table,allGoodUrl){
		   	    table.render({
				      elem: '#goodList'
				      ,height: 312
				      ,url: allGoodUrl //数据接口
				      ,page: {theme:'#f0ad4e'} //开启分页
				      ,cols: [[ //表头
				         {field: 'goodId', title: '条码', width:150, sort: true, fixed: 'left'}
				        ,{field: 'goodName', title: '商品名', width:110}
				        ,{field: 'supplierName', title: '供应商', width:210, sort: true}
				        ,{field: 'price', title: '价格', width:80} 
				        ,{field: 'category', title: '商品类别', width: 100}
				        ,{field: 'quantity', title: '库存', width:70}
				        ,{field: 'specification', title: '规格', width: 90}
				        ,{field: 'shelve', title: '货架', width: 70}
		      			,{field: '', title: '', width: 90,align : 'center',toolbar : '#layUiDeleteGood'}
		      			,{field: '', title: '', width: 90, align : 'center',toolbar : '#layUiInputGood'}
		      			,{field: '', title: '', width: 90,align : 'center',toolbar : '#layUiOutputGood'}
				     ]]
				  });
				  table.on('tool(test)',function(obj){
				  	  var data = obj.data;
				  	  var layEvent = obj.event;
				  	  if(layEvent === 'addGood'){
						  window.location = '/addGoods.jsp';
				  	  }else if(layEvent === 'deleteGood'){
				  	  	  layer.confirm('真的删除该货物及其所有信息吗?',function(index){
				  	  	  	 obj.del();
				  	  	  	 layer.close(index);
				  	  	  	 var url = '${pageContext.request.contextPath}/good/deleteGoodById';
				  	  	  	 var goodId = data.goodId;
				  	  	  	 $.post(url,{'goodId':goodId},function(rs){
				  	  	  	 	 if(rs == "success"){
				  	  	  	 	 	alert("货物删除成功");
				  	  	  	 	 }else if(rs == "fail"){
				  	  	  	 	 	alert("货物删除失败");
				  	  	  	 	 }
				  	  	  	 });
				  	  	  });
				  	  }else if(layEvent === 'inputGood'){
				  	      var goodId = data.goodId;
				  	      var quantity = $("#inputNumber").val();
				  	  	  var url = '${pageContext.request.contextPath}/good/updateGood.action';
				  	  	  layer.open({
				  	  	  	 title:['货物入库'],
				  	  	  	 skin:'body .demo-class .layui-layer-title{background:#FFE4BB;}',
							    area:['650px','270px'],
						   		content:$("#inputLayer"),   //   './views/addSupplier.jsp' 若嵌入页面，应该设置type=2
						   		yes: function(index,layero){    //点击“确认”按钮响应  .children('option:selected').val()
						   			layer.close(index);
						   		    $.post(url,{'goodId':goodId,'quantity':quantity},function(rs){
						   		        if(rs ==  "success"){
						   		        	alert("入库 成功");
						   		        }else if(rs == "fail"){
						   		        	alert("入库失败");
						   		        }
						   		    });
						   		}
				  	  	  })
				  	  }else if(layEvent === 'outputGood'){
				  	  	  var goodId = data.goodId;
				  	      var quantity = $("#outputNumber").val();
				  	  	  var url = '${pageContext.request.contextPath}/good/updateGood.action';
				  	  	  layer.open({
				  	  	  	 title:['货物出库'],
				  	  	  	 skin:'body .demo-class .layui-layer-title{background:#FFE4BB;}',
							    area:['650px','270px'],
						   		content:$("#outputLayer"),   //   './views/addSupplier.jsp' 若嵌入页面，应该设置type=2
						   		yes: function(index,layero){    //点击“确认”按钮响应  .children('option:selected').val()
						   			layer.close(index);
						   		    $.post(url,{'goodId':goodId,'quantity':quantity},function(rs){
						   		        if(rs ==  "success"){
						   		        	alert("出库 成功");
						   		        }else if(rs == "fail"){
						   		        	alert("出库失败");
						   		        }
						   		    });
						   		}
				  	  	  })
				  	  }
				  })
		   }
		</script>
  </body>
</html>
