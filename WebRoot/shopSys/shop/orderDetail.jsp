<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>商家平台</title>
		<link href="${pageContext.request.contextPath }/shopSys/style/css/orderdetail.css" rel="stylesheet" type="text/css">
	</head>
<body>
	<!-- 页面标题 -->
	<h2>订单详情</h2>
	<hr>

	<!-- 主内容区域（数据列表或表单显示） -->
	<h3>菜品信息</h3>
	<div id="MainArea">
		<table align="center" cellspacing="0" cellpadding="0" style="background-color:#F5DEB3">
			<!-- 表头-->
			<thead>
				<tr align="center" valign="middle" ">
					<td>菜名</td>
					<td>单价</td>
					<td>数量</td>
				</tr>
			</thead>
			<!--显示数据列表 -->
			<tbody id="TableData">
				<c:forEach items="${requestScope.orderDetail}" var="od">
			 		<tr align="center">
			 		<c:forEach items="${requestScope.foods}" var="food">
			 			<c:if test="${food.food_id==od.food_id }">
			 				<c:set var="foodName" value="${food.food_name}"/>
			 				<c:set var="foodPrice" value="${food.food_price }"/>
			 			</c:if>
			 		</c:forEach>
				 		<td><c:out value="${foodName}"/> </td>
				 		<td><c:out value="${foodPrice}"/></td>
				 		<td>${od.food_count}</td>
			 		</tr>
			 	</c:forEach>
			</tbody>
		</table>
		
		<h3>配送信息</h3>
		<!-- 配送信息 -->
		<div id="deliver">
			<table align="center" cellspacing="0" cellpadding="0" >
				<tr>
					<td style="text-align:center">用户名：</td>
					<td>${requestScope.USER_MSG.user_name }</td>
				</tr>
				<tr>
					<td style="text-align:center">联系电话：</td>
					<td>${requestScope.USER_MSG.user_pho }</td>
				</tr>
				<tr>
					<td style="text-align:center">配送地址：</td>
					<td>${requestScope.USER_MSG.user_add }</td>
				</tr>
			</table>
		</div>
		
		<!-- 其他功能超链接 -->
		<div id="TableTail" align="center" style="margin-top:10px">
			 <a href="javascript:history.go(-1);" class="FunctionButton">返回</a>
		</div>
	</div>
</body>
</html>
