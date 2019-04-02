<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<shiro:hasPermission name="good:add"></shiro:hasPermission>
<!-- /shiro:hasRole -->
<!-- <shiro:hasRole name="user"></shiro:hasRole>  -->
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="./UI/layui/css/layui.css">
	<style type="text/css">
		.right-panel{
			background-color:#FFE4BB;
			margin-top:-200px;
			width:60px;
			height:350px;
			padding:5px;
		}
	</style>
	<script type="text/javascript" src="UI/js/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="UI/js/json2.js"></script>
    <script type="text/javascript" src="UI/js/jquery-1.11.0.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="./UI/layui/layui.js"></script>
 
	<script type="text/javascript">
	  	   layui.use('element', function(){
			  var element = layui.element;
			  //…
		   });
		   $(document).ready(function(){
 			   categories()
		   	   //suppliers();
		   });
		   function categories(){
		        $.ajax({
		   	   	    url:"${pageContext.request.contextPath}/category/allCategory.action",
		   	   	    type:"post",
		   	   	    data:{},
		   	   	    dataType:"json",
		   	   	    success:function(rs){
		   	   	    	var strHtml = "<option value=\"\"></option>";
		   	   	    	//"field":["玩具","食品","婴儿用品","生活用品","文具","电器"],
		   	   	    	for(var i=0; i<rs.field.length; i++){
		   	   	    		strHtml += "<option value=\""+rs.field[i]+"\">"+rs.field[i]+"</option>";
		   	   	    	}
		   	   	    	document.getElementById("category").innerHTML = strHtml;
		   	   	    },
		   	   	    error:function(XMLHttpRequest,textStatus,errorThrown){
		   	   	        alert("${pageContext.request.contextPath}/category/allCategory.action");
						alert("status:  "+XMLHttpRequest.status+"    readyState: "+XMLHttpRequest.readyState
							+"\n responseText:  "+XMLHttpRequest.responseText);
					}
		   	   });
		   }
		   function suppliers(){
		       var category = $("#category").children('option:selected').val();
		   	   $.ajax({
				  url:"${pageContext.request.contextPath}/supplier/findSupplierByCategory.action",
				  type:'post',
				  data:{"category":category},
				  dataType:'json',
				  success:function(rs){
					  var strHtml = "<option value=\"\"></option><option value=\"其他\">其他</option>";
					  for(var i=0; i<rs.length; i++){
					   	 strHtml += "<option value=\""+rs[i].supplierId+"\">"+rs[i].supplierName+"</option>";
					  }
					  document.getElementById("supplier").innerHTML = strHtml;
			      },
			  });		   	  
		   }
		   function supplierInfo(){
		   	    layui.use('table', function(){
				  var table = layui.table;
				  var supplierId = $("#supplier").children('option:selected').val();  //得到的是选中的option的value值而不是框中显示的信息
				  var category = $("#category").children('option:selected').val();
				  if(supplierId == "其他"){
				        layui.use('layer', function(){ 
							var url = '${pageContext.request.contextPath}/supplier/insertSupplier.action';
							//var layer = layui.layer;
							layer.open({  //layer已在前边定义
							    title:['添加供应商'],
							    skin:'body .demo-class .layui-layer-title{background:#FFE4BB;}',
							    area:['600px','260px'],
						   		content:$("#layerContent"),   //   './views/addSupplier.jsp' 若嵌入页面，应该设置type=2
						   		yes: function(index,layero){
						   			var supplierName = $("#lSupplierName").val();
								    var phone = $("#lPhone").val();
								    var address = $("#lAddress").val();
								    var personInCharge = $("#lPersonInCharge").val();
								    var startTime = $("#lStartTime").val();
						   			layer.close(index);
						   			
						   		    $.post(url,{'supplierName':supplierName,'startTime':startTime,'personInCharge':personInCharge
						   		     , 'address':address,'phone':phone,'request':'InAddGood','category':category},function(rs){
						   		           //插入成功回调函数
						   		     	    table.render({
											    elem: '#supplierInfo'
											    ,height: 85
											    ,url: encodeURI('${pageContext.request.contextPath}/supplier/findBySupplierName.action?supplierName='+supplierName) //数据接口
											    ,page:false//开启分页
											    ,cols: [[ //表头
											      {field: 'supplierId', title: 'ID', width:80, fixed: 'left'}
											      ,{field: 'supplierName', title: '供应商', width:210}
											      ,{field: 'address', title: '联系地址', width:242 }
											      ,{field: 'startTime', title: '开始合作时间', width:150} 
											      ,{field: 'personInCharge', title: '负责人', width: 100}
											      ,{field: 'phone', title: '联系方式', width: 120 }
											      ,{field: 'category', title: '供货种类', width: 140 }
											    ]]
										   });
										   supplierId = rs.data[0].supplierId;
						   		     });
						   		}
						    });
					   });
				  }else{
				  	   table.render({
					      elem: '#supplierInfo'
					      ,height: 85
					      ,url: encodeURI('${pageContext.request.contextPath}/supplier/findBySupplierId.action?supplierId='+supplierId) //数据接口
					      ,page:false//开启分页
					      ,cols: [[ //表头
					          {field: 'supplierId', title: 'ID', width:80, fixed: 'left'}
					          ,{field: 'supplierName', title: '供应商', width:210}
					          ,{field: 'address', title: '联系地址', width:242 }
					          ,{field: 'startTime', title: '开始合作时间', width:150} 
					          ,{field: 'personInCharge', title: '负责人', width: 100}
					          ,{field: 'phone', title: '联系方式', width: 120 }
					          ,{field: 'category', title: '供货种类', width: 140 }
					    ]]
				     });
				  }
		 	   });
		   }
		   
	</script>

  </head>
  
  <body>
  	  <!-- 模态框呢呢绒 -->
  	  <div style="display:none" id="layerContent">
  	  	  <div>
  	  	  	  <div>供应商&ensp;&ensp;<input name="lSupplierName" id="lSupplierName"></div>
	  	  	  <div style="margin-top:15px">负责人&ensp;&ensp;<input name="lPersonInCharge" id="lPersonInCharge"></div>
	  	  	  <div style="margin-top:15px">开始合作时间&ensp;&ensp;<input id="lStartTime" name="lStartTime" placeholder="格式：yy-MM-dd"></div>
  	  	  </div>
  	      <div style="margin-top:-130px;margin-left:270px">
	  	      <div style="margin-top:15px">联系方式&ensp;&ensp;<input name="lPhone" id="lPhone"></div>
	  	      <div style="margin-top:15px">联系地址&ensp;&ensp;<input name="lAddress" id="lAddress"></div>
  	      </div>
  	  </div>
  	  
      <jsp:include page="../head.jsp" flush="true"></jsp:include>

      <div style="margin-left:100px;margin-top:30px">
       <div id="left" style="width:500px">
      	  <form action="good/addGood.action" method="post">
      	  	  <div>
      	  	      <div class="input-group">
				     <span class="input-group-addon" id="sizing-addon2" style="background-color:#FFE4BB">
				     	 <i class="layui-icon layui-icon-username"></i>条码
				     </span>
				     <input type="text" name="code" class="form-control" placeholder="Code" aria-describedby="sizing-addon2">
				  </div>
				  <div class="input-group" class="input-group" style="margin-top:20px;">
		      	  	  <span type="button" class="btn btn-default dropdown-toggle" style="background-color:#FFE4BB" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="layui-icon layui-icon-layouts"></i>分类</span>
					  <select name="category" id="category" onchange="javascript:suppliers()" lay-verify="required" id="roleList" style="width:431px;height:36px;margin-left:68px;margin-top:-36px">
					  </select>
		          </div>
		          <div class="input-group" style="margin-top:20px">
				     <span class="input-group-addon" id="sizing-addon2" style="background-color:#FFE4BB;">
				     	 <i class="layui-icon layui-icon-username"></i>商品名
				     </span>
				     <input type="text" name="goodName" class="form-control" placeholder="goodName" aria-describedby="sizing-addon2">
				  </div>
      	  	  </div>
			  <div style="margin-left:550;margin-top:-164px">
			  	  <div class="input-group" style="margin-top:20px">
				     <span class="input-group-addon" id="sizing-addon2" style="background-color:#FFE4BB;">
				     	 <i class="layui-icon layui-icon-about"></i>规格
				     </span>
				     <input type="text" name="specification" class="form-control" placeholder="Specification" aria-describedby="sizing-addon2" style="width:430px">
				  </div>
				  <div class="input-group" class="input-group" style="margin-top:20px;margin-top:20px">
		      	  	  <span type="button" class="btn btn-default dropdown-toggle" style="background-color:#FFE4BB" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="glyphicon glyphicon-indent-right"></i>货架</span>
					  <select name="shelve" lay-verify="required" id="shelve" style="width:431px;height:34px;margin-left:68px;margin-top:-34px">
					      <option value=""></option>
					      <option value="1">1-1-1</option>
					      <option value="8">1-1-2</option>
						  <option value="3">1-1-3</option>
						  <option value="6">1-1-4</option>
						  <option value="2">1-2-1</option>
						  <option value="1">1-2-2</option>
						  <option value="1">1-2-3</option>
					      <option value="8">1-2-4</option>
						  <option value="3">2-1-1</option>
						  <option value="6">2-1-2</option>
						  <option value="2">2-2-1</option>
						  <option value="1">2-2-2</option>
					 </select>
		         </div>
		         <div class="input-group" style="margin-top:20px">
				     <span class="input-group-addon" id="sizing-addon2" style="background-color:#FFE4BB;">
				     	 <i class="layui-icon layui-icon-username"></i>商品质量
				     </span>
				     <input type="text" name="quantity" class="form-control"style="width:405px" placeholder="Quantity" aria-describedby="sizing-addon2">
				  </div>
			  </div>
			  <div>
			      <div class="input-group" style="margin-top:20px">
				     <span class="input-group-addon" id="sizing-addon2" style="background-color:#FFE4BB;">
				     	 <i class="layui-icon layui-icon-username"></i>商品价格
				     </span>
				     <input type="text" name="price" class="form-control" style="width:405px" placeholder="Price" aria-describedby="sizing-addon2">
				  </div>
				  <div class="input-group" class="input-group" style="margin-top:-35px;margin-left:550px">
		      	  	  <span type="button" class="btn btn-default dropdown-toggle" style="background-color:#FFE4BB" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="layui-icon layui-icon-friends"></i>供应商</span>
					  <select name="supplier" id="supplier" onchange="javascript:supplierInfo()" lay-verify="required" id="roleList" style="width:420px;height:36px;margin-left:79px;margin-top:-36px">
				<!-- 保存id——suppliername，取id -->
				          <option value="notice">请先选择商品分类</option>
					  </select>
		         </div>
			  </div>
			   
			  <div style="width:1050px;height:auto;background-color:#FFE4BB;margin-top:30px">
			  	  <div style="margin-left:490px;font-size:16px;padding-top:10px;height:40px">供应商信息</div>
			  	  <div> <table id="supplierInfo" lay-filter="test"></table></div>
			  </div>
			  <div><button class="btn btn-warning" type="submit" style="margin-left:410px;width:250px;margin-top:20px">添加商品</button></div>
	       </form>
      </div></div>
  </body>
</html>
