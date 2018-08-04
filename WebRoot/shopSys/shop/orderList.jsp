<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>商家平台</title>
		<link href="${pageContext.request.contextPath }/shopSys/style/css/orderlist.css" rel="stylesheet" type="text/css">
	</head>
<body>
	
	<h2>订单列表</h2>
	<hr>
	<!-- 主内容区域（数据列表或表单显示） -->
	<div id="MainArea">
		<table align="center" cellspacing="0" cellpadding="0">
			<!-- 表头-->
			<thead>
				<tr align="center" valign="middle" style="background-color:#F5DEB3">
					<td>序号</td>
					<td>订单编号</td>
					<td>下单日期</td>
					<td>总金额</td>
					<td>支付状态</td>
					<td>配送状态</td>
					<td>收货状态</td>
					<td>操作</td>
				</tr>
			</thead>
			<!--显示数据列表 -->
			<tbody id="TableData">
				<c:forEach items="${requestScope.pageBean.pageData}" var="o" varStatus="vs">
					<tr height="60" align="center">
						<td>${vs.count }</td>
						<td>${o.order_id}</td>
						<td>${o.order_time}</td>
						<td>${o.order_price}</td>
						<c:choose >
				 			<c:when test="${o.order_pay==0}">
				 				<td>未结账</td>
				 			</c:when>
				 			<c:otherwise>
				 				<td>已结账</td>
				 			</c:otherwise>
				 		</c:choose>
				 		<c:choose >
				 			<c:when test="${o.order_deli==0}">
				 				<td>未配送</td>
				 			</c:when>
				 			<c:otherwise>
				 				<td>已配送</td>
				 			</c:otherwise>
				 		</c:choose>
				 		<c:choose >
				 			<c:when test="${o.order_get==0}">
				 				<td>未收货</td>
				 			</c:when>
				 			<c:otherwise>
				 				<td>已收货</td>
				 			</c:otherwise>
				 		</c:choose>
						<td>
							<a href="${pageContext.request.contextPath }/ShopServlet?method=getOrderDetail&orderId=${o.order_id}" >详细</a>
							<a href="${pageContext.request.contextPath }/ShopServlet?method=confirmDeli&orderId=${o.order_id}" >确认配送</a>
						</td>
					</tr>
				</c:forEach>
		 		
			</tbody>
		</table>
		<!-- 其他功能超链接 -->
	<div id="TableTail" align="center" style="margin-top:10px">
		当前${requestScope.pageBean.currentPage }/${requestScope.pageBean.totalPage }页     &nbsp;&nbsp;
		<a href="${pageContext.request.contextPath }/ShopServlet?method=getOrderList&currentPage=1">首页</a>
		<a href="${pageContext.request.contextPath }/ShopServlet?method=getOrderList&currentPage=${requestScope.pageBean.currentPage-1}">上一页 </a>
		<a href="${pageContext.request.contextPath }/ShopServlet?method=getOrderList&currentPage=${requestScope.pageBean.currentPage+1}">下一页 </a>
		<a href="${pageContext.request.contextPath }/ShopServlet?method=getOrderList&currentPage=${requestScope.pageBean.totalPage}">末页</a>
    </div> 
	</div>
</body>
</html>
