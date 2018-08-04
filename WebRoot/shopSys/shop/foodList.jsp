<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>商家后台</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<style type="text/css">
		body {
    		background-color:#EEE8AA;
    	}
    	#QueryArea{
    		margin-top:-20px;
    	}
		#search{
			background-color: #F4A460;
			color:white;
			border-radius: 6px;
			font-size:15px;
		}
		#search input:hover{
			background:linear-gradient(#F4A460,#F5DEB3);
		}
		#add_button{
			float:left;
			background-color:#F0C17D;
			font-family:微软雅黑;
		}
		#add_button a{
			text-decoration:none;
			border:2px solid orange;
			border-radius: 6px;
			color:black;
			font-size:18px;
		}
		#add_button a:hover{
			background:linear-gradient(#F5DEB3,#F4A460); 
		}
		table{
			margin:auto;
			width:100%;
			border:3px solid orange;
			border-radius: 8px;
			color:#262626;
			box-shadow:0px 0px 8px 4px #aaa;
		}
		td{
			font-size:20px;
			width:50px;
			border:1px solid orange;
		}
		h2{
			margin-top:2%;
			text-align:center;
			font-size:35px;
			color:black;
			text-shadow: 0 1px 0 #ccc,0 2px 0 #c9c9c9,0 3px 0 #bbb,0 4px 0 #b9b9b9,0 5px 0 #aaa,0 6px 1px rgba(0,0,0,.1),0 0 5px rgba(0,0,0,.1),0 1px 3px rgba(0,0,0,.3),0 3px 5px rgba(0,0,0,.2),0 5px 10px rgba(0,0,0,.25),0 10px 10px rgba(0,0,0,.2),0 20px 20px rgba(0,0,0,.15); 
		}
		hr{
			width:100%;
			height:10px;
			margin-bottom:20px;
			border:none;
			border-top:10px groove skyblue;"
		}
		a{
			color:#D2691E;
			text-decoration:none;
		}
		a:hover{
			border-bottom: 2px solid orange;
		}
		input:hover{
			background:linear-gradient(#F5DEB3,#F4A460); 
 			color: black;
		}
		</style>
		<script type="text/javascript">
			function deleteEnsure(id){
				if(confirm("确定删除该菜品吗")){
					window.location.href="${pageContext.request.contextPath}/ShopServlet?method=delete&id="+id;
				}else{
				
				}
			}
		</script>
	</head>
	<body>
	
	<h2>菜品列表</h2>
	<hr>
	<!-- 过滤条件 -->
	<div id="QueryArea">
		<form action="${pageContext.request.contextPath }/ShopServlet" method="post">
			<input type="hidden" name="method" value="search">
			<input style="font-size:15px" type="text" name="keyword" placeholder="请输入菜品名称" title="请输入菜品名称">
			<input id="search" type="submit" value="搜索">
		</form>
	</div>
	<!-- 主内容区域（数据列表或表单显示） -->
	<div id="MainArea">
	    <table align="center" cellspacing="0" cellpadding="0">
	        <!-- 表头-->
	        <thead>
	            <tr align="center" valign="middle" style="background-color:#F5DEB3">
					<td>菜品编号</td>
					<td>菜品名称</td>
					<td>价 格</td>
					<td>操 作</td>
				</tr>
			</thead>	
			<!--显示数据列表 -->
	        <tbody id="TableData">
				<c:choose>
					<c:when test="${not empty requestScope.FOODLIST }">
						<c:forEach items="${requestScope.FOODLIST}" var="food" varStatus="vs">
							<tr align="center">
								<td>${vs.count }</td>
								<td>${food.food_name }</td>
								<td>${food.food_price}</td>
								<td>
									<a href="${pageContext.request.contextPath}/ShopServlet?method=show&id=${food.food_id}" >更新</a>&nbsp;&nbsp;				
									<a href="javascript:void(0)" onclick="deleteEnsure(${food.food_id})">删除</a>				
								</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<center>没有你要找的数据，请先添加菜品！</center>
							<br>
						</tr>
					</c:otherwise>
		        </c:choose>
	        </tbody>
	    </table>
		
	   <!-- 其他功能超链接 -->
		<div align="center" style="margin-top:10px">
			<div id="add_button">
				<a href="shopSys/shop/addFood.jsp">&nbsp;&nbsp;添加菜品&nbsp;&nbsp;</a>
			</div>
			当前${requestScope.pageBean.currentPage }/${requestScope.pageBean.totalPage }页     &nbsp;&nbsp;
			<a href="${pageContext.request.contextPath }/ShopServlet?method=getAllFood&currentPage=1">首页</a>
			<a href="${pageContext.request.contextPath }/ShopServlet?method=getAllFood&currentPage=${requestScope.pageBean.currentPage-1}">上一页 </a>
			<a href="${pageContext.request.contextPath }/ShopServlet?method=getAllFood&currentPage=${requestScope.pageBean.currentPage+1}">下一页 </a>
			<a href="${pageContext.request.contextPath }/ShopServlet?method=getAllFood&currentPage=${requestScope.pageBean.totalPage}">末页</a>
	    </div> 
	</div>
	</body>
</html>
